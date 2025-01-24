package com.bytedance.core.architecture.message

import android.app.Application
import androidx.annotation.StringRes

/**
 * 描述：消息管理者（全局消息初始化、展示等）
 *
 * @author zhangrq
 * createTime 2024/8/21 上午9:20
 */
object MessageManager {

    private lateinit var globalMessageController: MessageController

    @JvmStatic
    fun initGlobalMessage(
        application: Application,
        messageController: MessageController,
        messageCollector: MessageCollector,
    ) {
        globalMessageController = messageController
        messageCollector.initGlobalCollect(application, messageController)
    }

    @JvmOverloads
    @JvmStatic
    fun showGlobalMessage(@StringRes message: Int, isShort: Boolean = true) {
        globalMessageController.sendMessage(message, isShort)
    }

    @JvmOverloads
    @JvmStatic
    fun showGlobalMessage(message: String, isShort: Boolean = true) {
        globalMessageController.sendMessage(message, isShort)
    }
}