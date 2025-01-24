package com.bytedance.douyin.core.network.model

import kotlinx.serialization.Serializable

/**
 * 描述：
 *
 * @author zhangrq
 * createTime 2024/12/17 18:02
 */
@Serializable
data class FakeNetworkSentences(
    val name: String,
    val from: String,
)