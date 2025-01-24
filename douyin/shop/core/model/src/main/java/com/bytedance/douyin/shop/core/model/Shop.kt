package com.bytedance.douyin.shop.core.model


/**
 * 描述：
 *
 * @author zhangrq
 * createTime 2024/9/5 下午5:10
 */
data class Shop(
    // 1：按钮，2：商品，3：活动
    val type: Int,
    val buttons: List<ShopButton>? = null,// type为1时用
    // 下面的type为非1时用
    val id: Int? = null,
    val image: String? = null,
    val background: String? = null,
    val title: String? = null,
    val price: Double? = null,
    val soldNum: String? = null,
)

data class ShopButton(
    val id: Int,
    val title: String?,
    val image: String?,
)