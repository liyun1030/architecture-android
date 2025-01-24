package com.bytedance.core.common.startup

import android.app.Application
import android.content.Context
import androidx.startup.Initializer
import com.bytedance.core.common.util.AppProvider

/**
 * 描述：
 *
 * @author zhangrq
 * createTime 2024/12/18 下午3:14
 */
class BaseCommonInitializer : Initializer<Unit> {

    override fun create(context: Context) {
        AppProvider.init(context as Application)
    }

    override fun dependencies(): MutableList<Class<out Initializer<*>>> {
        return mutableListOf()
    }
}