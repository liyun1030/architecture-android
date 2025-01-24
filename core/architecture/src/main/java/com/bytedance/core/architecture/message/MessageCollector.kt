package com.bytedance.core.architecture.message

import android.app.Application
import androidx.lifecycle.Lifecycle
import kotlinx.coroutines.CoroutineScope

/**
 * 描述：消息收集者
 *
 * @author zhangrq
 * createTime 2024/8/13 下午5:24
 */
interface MessageCollector {
    fun initCollect(
        messageController: MessageController,
        coroutineScope: CoroutineScope,
        lifecycle: Lifecycle,
    )

    fun initGlobalCollect(application: Application, messageController: MessageController)
}