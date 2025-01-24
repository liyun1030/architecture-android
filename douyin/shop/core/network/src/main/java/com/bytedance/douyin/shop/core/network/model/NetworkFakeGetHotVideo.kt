package com.bytedance.douyin.shop.core.network.model

import kotlinx.serialization.Serializable

/**
 * 描述：
 *
 * @author zhangrq
 * createTime 2024/12/16 10:40
 */
@Serializable
data class NetworkFakeGetHotVideo(
    val itemList: List<NetworkFakeGetHotVideoItem> = listOf(),
)

@Serializable
data class NetworkFakeGetHotVideoItem(
    val `data`: NetworkFakeGetHotVideoItemData = NetworkFakeGetHotVideoItemData(),
)

@Serializable
data class NetworkFakeGetHotVideoItemData(
    val cover: NetworkFakeGetHotVideoItemDataCover = NetworkFakeGetHotVideoItemDataCover(),
    val id: Int = 0,
    val title: String = "",
)

@Serializable
data class NetworkFakeGetHotVideoItemDataCover(
    val blurred: String = "",
    val feed: String = "",
)