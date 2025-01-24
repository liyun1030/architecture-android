package com.bytedance.douyin.core.login.login

import android.app.Activity
import android.content.Context
import com.bytedance.douyin.core.datastore.AppPreferencesDataSource
import com.bytedance.douyin.core.login.di.LoginEntryPoint
import com.bytedance.douyin.core.router.Router
import dagger.hilt.android.EntryPointAccessors
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

/**
 * 描述：
 *
 * @author zhangrq
 * createTime 2024/3/20 下午5:33
 */
object LoginManager {
    private lateinit var preferencesDataSource: AppPreferencesDataSource
    private lateinit var applicationScope: CoroutineScope
    private lateinit var isLoggedFlow: Flow<Boolean>
    private val autoLoginJobList = mutableListOf<Job>()

    @Suppress("MemberVisibilityCanBePrivate")
    var isCurrentLogged: Boolean = false

    fun init(context: Context) {
        val loginEntryPoint =
            EntryPointAccessors.fromApplication(context, LoginEntryPoint::class.java)
        preferencesDataSource = loginEntryPoint.appPreferencesDataSource()
        applicationScope = loginEntryPoint.applicationScope()

        isLoggedFlow = preferencesDataSource.userData.map { it.user }.distinctUntilChanged()
            .map { it.id != 0 && it.token.isNotEmpty() }
        // 启动监听
        applicationScope.launch {
            isLoggedFlow.collect {
                isCurrentLogged = it
            }
        }
    }

    fun checkLogin(activity: Activity, auto: Boolean = true, next: () -> Unit) {
        if (isCurrentLogged) {
            // 已经登录
            next()
        } else {
            // 未登录
            // -检查是否需要自动回调
            if (auto) {
                // 需要自动回调
                applicationScope.launch(Dispatchers.Main) {
                    isLoggedFlow.collect { isLogged ->
                        // 通知
                        if (isLogged) {
                            next()
                        }
                    }
                }.also {
                    autoLoginJobList.add(it)
                }
            }
            // -跳转
            Router.Login.startLoginActivity(activity)
        }
    }

    fun logout() {
        applicationScope.launch {
            preferencesDataSource.clearUser()
        }
    }

    // 设置当前的状态，防止在登录页面，没操作，然后没通知
    fun clearAutoLoginJob() {
        // 清空
        for (job in autoLoginJobList) {
            job.cancel()
        }
        autoLoginJobList.clear()
    }

}