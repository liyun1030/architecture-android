package com.bytedance.douyin.core.network.model

/**
 * 描述：
 *
 * @author zhangrq
 * createTime 2024/11/26 17:47
 */
data class NetworkUser(
    val id: Int,
    val token: String,
    val account: String,
    val createdAt: String,
)