package com.bytedance.douyin.core.router.startup

import android.content.Context
import androidx.startup.Initializer
import com.bytedance.douyin.core.router.Router

/**
 * 描述：
 *
 * @author zhangrq
 * createTime 2024/3/20 上午9:23
 */
class RouterInitializer : Initializer<Unit> {
    override fun create(context: Context) {
        Router.init(context)
    }

    override fun dependencies(): MutableList<Class<out Initializer<*>>> {
        return mutableListOf()
    }
}