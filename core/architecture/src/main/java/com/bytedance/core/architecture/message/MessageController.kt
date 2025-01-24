package com.bytedance.core.architecture.message

import androidx.annotation.StringRes
import kotlinx.coroutines.flow.Flow

/**
 * 描述：消息-控制者（发送、清空等）
 *
 * @author zhangrq
 * createTime 2024/8/13 上午10:31
 */
interface MessageController {
    val messages: Flow<Message?>

    fun sendMessage(@StringRes message: Int, isShort: Boolean = true)

    fun sendMessage(message: String, isShort: Boolean = true)

    fun clearMessages()

    fun setMessageShown(id: Long)
}