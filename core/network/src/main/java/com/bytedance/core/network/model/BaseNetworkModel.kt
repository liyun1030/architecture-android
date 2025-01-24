package com.bytedance.core.network.model

/**
 * 描述：
 *
 * @author zhangrq
 * createTime 2024/8/29 下午4:50
 */
interface BaseNetworkModel<T> {
    /**
     * 是否规则（公司内的，可能会有多个）成功
     */
    fun isRuleSuccess(): Boolean

    /**
     * 网络返回的码
     */
    fun code(): Int?

    /**
     * 网络返回的消息
     */
    fun message(): String?

    /**
     * 网络返回的数据
     */
    fun data(): T?
}