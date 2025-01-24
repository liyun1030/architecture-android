package com.bytedance.core.architecture.message.controller

import androidx.annotation.StringRes
import com.bytedance.core.architecture.message.Message
import com.bytedance.core.architecture.message.MessageController
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import java.util.UUID

/**
 * 描述：消息控制者（发送者）的默认实现
 *
 * @author zhangrq
 * createTime 2024/8/13 下午5:24
 */
class DefaultMessageController : MessageController {

    private val messagesList: MutableStateFlow<List<Message>?> = MutableStateFlow(null)

    override val messages: Flow<Message?> =
        messagesList.map { it?.firstOrNull() }.distinctUntilChanged()

    override fun sendMessage(@StringRes message: Int, isShort: Boolean) {
        internalShowMessage(message, isShort)
    }

    override fun sendMessage(message: String, isShort: Boolean) {
        internalShowMessage(message, isShort)
    }

    override fun clearMessages() {
        messagesList.update {
            null
        }
    }

    override fun setMessageShown(id: Long) {
        messagesList.update { currentMessages ->
            currentMessages?.filterNot { it.id == id }
        }
    }

    private fun internalShowMessage(message: Any, isShort: Boolean) {
        messagesList.update { currentMessages ->
            (currentMessages ?: listOf()) + Message(
                id = UUID.randomUUID().mostSignificantBits,
                message = message,
                isShort = isShort,
            )
        }
    }
}