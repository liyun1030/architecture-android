package com.bytedance.douyin.shop.core.data.model

import com.bytedance.douyin.shop.core.model.Shop
import com.bytedance.douyin.shop.core.model.ShopButton
import com.bytedance.douyin.shop.core.network.model.NetworkShop
import com.bytedance.douyin.shop.core.network.model.NetworkShopButton

/**
 * 描述：
 *
 * @author zhangrq
 * createTime 2024/11/29 09:27
 */
// 商城，网络类->外部类。
fun NetworkShop.asExternalModel() = Shop(
    type,
    buttons?.map { it.asExternalModel() },
    id,
    image,
    background,
    title,
    price,
    soldNum,
)

// 商城按钮，网络类->外部类。
fun NetworkShopButton.asExternalModel() = ShopButton(id, title, image)