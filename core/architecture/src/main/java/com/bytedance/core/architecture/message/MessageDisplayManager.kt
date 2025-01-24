package com.bytedance.core.architecture.message

import androidx.annotation.StringRes

/**
 * 描述：消息显示-管理者（展示、清空等）
 *
 * @author zhangrq
 * createTime 2024/8/15 上午9:26
 */
interface MessageDisplayManager {
    val messageController: MessageController

    fun showMessage(@StringRes message: Int, isShort: Boolean = true) {
        messageController.sendMessage(message, isShort)
    }

    fun showMessage(message: String, isShort: Boolean = true) {
        messageController.sendMessage(message, isShort)
    }

    fun clearMessages() {
        messageController.clearMessages()
    }
}