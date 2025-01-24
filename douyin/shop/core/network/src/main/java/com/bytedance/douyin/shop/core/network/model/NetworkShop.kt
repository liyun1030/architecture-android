package com.bytedance.douyin.shop.core.network.model

/**
 * 描述：
 *
 * @author zhangrq
 * createTime 2024/12/4 16:28
 */
data class NetworkShop(
    // 1：按钮，2：商品，3：活动
    val type: Int,
    val buttons: List<NetworkShopButton>? = null,// type为1时用
    // 下面的type为非1时用
    val id: Int? = null,
    val image: String? = null,
    val background: String? = null,
    val title: String? = null,
    val price: Double? = null,
    val soldNum: String? = null,
)

data class NetworkShopButton(
    val id: Int,
    val title: String?,
    val image: String?,
)