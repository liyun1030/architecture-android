package com.bytedance.core.common.util

import android.app.Application
import android.content.Context


/**
 * 描述：
 *
 * @author zhangrq
 * createTime 2024/12/18 15:23
 */
object AppProvider {
    private lateinit var application: Application

    fun init(application: Application) {
        this.application = application
    }

    fun getApplication() = application

    fun getApplicationContext(): Context = application.applicationContext
}