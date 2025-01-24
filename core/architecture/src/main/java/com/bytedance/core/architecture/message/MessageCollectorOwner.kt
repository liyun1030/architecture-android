package com.bytedance.core.architecture.message

/**
 * 描述：消息收集者-持有者
 *
 * @author zhangrq
 * createTime 2024/8/15 上午9:26
 */
interface MessageCollectorOwner {
    val messageCollector: MessageCollector
}