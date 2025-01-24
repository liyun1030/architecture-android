package com.bytedance.core.architecture.message

/**
 * 描述：消息
 *
 * @author zhangrq
 * createTime 2024/8/13 下午5:25
 */
data class Message(val id: Long, val message: Any, val isShort: Boolean)