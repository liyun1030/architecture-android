package com.bytedance.douyin.core.network.model

import kotlinx.serialization.Serializable

/**
 * 描述：
 *
 * @author zhangrq
 * createTime 2024/12/17 18:02
 */
@Serializable
data class FakeNetworkUser(
    val id: Int,
    val token: String,
    val account: String,
    val createdAt: String,
)

fun FakeNetworkUser.asNetworkUser() = NetworkUser(id, token, account, createdAt)