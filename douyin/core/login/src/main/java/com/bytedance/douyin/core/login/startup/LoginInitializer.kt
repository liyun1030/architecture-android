package com.bytedance.douyin.core.login.startup

import android.content.Context
import androidx.startup.Initializer
import com.bytedance.douyin.core.login.login.LoginManager

/**
 * 描述：
 *
 * @author zhangrq
 * createTime 2024/3/20 上午9:23
 */
class LoginInitializer : Initializer<Unit> {
    override fun create(context: Context) {
        LoginManager.init(context)
    }

    override fun dependencies(): MutableList<Class<out Initializer<*>>> {
        return mutableListOf()
    }
}