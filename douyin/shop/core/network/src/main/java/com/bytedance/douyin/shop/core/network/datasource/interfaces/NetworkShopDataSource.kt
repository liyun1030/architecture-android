package com.bytedance.douyin.shop.core.network.datasource.interfaces

import com.bytedance.douyin.shop.core.network.model.NetworkShop
import com.bytedance.douyin.shop.core.network.model.NetworkShopButton

/**
 * 描述：
 *
 * @author zhangrq
 * createTime 2024/11/28 10:23
 */
interface NetworkShopDataSource {
    suspend fun getShops(page: Int, size: Int): List<NetworkShop>

    // 模拟Item的【增删改】
    suspend fun addItem(): NetworkShop
    suspend fun deleteItem(id: Int)
    suspend fun updateItemTitle(id: Int, title: String)

    // 模拟Item-Item的（顶部按钮列表）【增删改】
    suspend fun addButtonItem(): NetworkShopButton
    suspend fun deleteButtonItem(id: Int)
    suspend fun updateButtonItemTitle(id: Int, title: String)
}