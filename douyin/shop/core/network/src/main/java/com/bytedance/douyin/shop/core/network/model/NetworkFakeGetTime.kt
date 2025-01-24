package com.bytedance.douyin.shop.core.network.model

import kotlinx.serialization.Serializable

/**
 * 描述:
 *
 * @author zhangrq
 * createTime 2024/11/26 下午5:29
 */
@Serializable
data class NetworkFakeGetTime(
    val date: String = "",
    val dateTime: String = "",
    val time: String = "",
    val weekday: String = "",
)