package com.bytedance.douyin.core.designsystem.util

import android.app.Activity
import android.app.Application
import android.os.Bundle
import com.bytedance.core.common.interfaces.empty.DefaultActivityLifecycleCallbacks
import com.bytedance.douyin.core.designsystem.di.DesignSystemEntryPoint
import com.google.android.material.color.DynamicColors
import dagger.hilt.android.EntryPointAccessors
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

/**
 * 描述：
 *
 * @author zhangrq
 * createTime 2024/12/25 15:41
 */
object DynamicColorManager {
    private val dynamicColorsActivityLifecycleCallbacks =
        object : DefaultActivityLifecycleCallbacks() {
            override fun onActivityPreCreated(activity: Activity, savedInstanceState: Bundle?) {
                DynamicColors.applyToActivityIfAvailable(activity)
            }
        }

    fun init(application: Application) {
        val loginEntryPoint =
            EntryPointAccessors.fromApplication(application, DesignSystemEntryPoint::class.java)

        val useDynamicColorFlow =
            loginEntryPoint.appPreferencesDataSource().userData.map { it.useDynamicColor }
                .distinctUntilChanged()

        val applicationScope = loginEntryPoint.applicationScope()

        // 启动监听
        applicationScope.launch {
            useDynamicColorFlow.collect {
                setUseDynamicColor(application, it)
            }
        }
    }

    private fun setUseDynamicColor(application: Application, use: Boolean) {
        if (use) {
            application.registerActivityLifecycleCallbacks(dynamicColorsActivityLifecycleCallbacks)
        } else {
            application.unregisterActivityLifecycleCallbacks(dynamicColorsActivityLifecycleCallbacks)
        }
    }
}