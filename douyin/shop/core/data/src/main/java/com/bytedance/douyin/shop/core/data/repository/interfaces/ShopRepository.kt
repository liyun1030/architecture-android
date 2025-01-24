package com.bytedance.douyin.shop.core.data.repository.interfaces

import com.bytedance.douyin.core.data.repository.refreshloadmore.interfaces.RefreshLoadMoreRepository
import com.bytedance.douyin.shop.core.model.Shop

/**
 * 描述：
 *
 * @author zhangrq
 * createTime 2024/12/4 下午2:14
 */
interface ShopRepository : RefreshLoadMoreRepository<Shop> {
    suspend fun getShops(page: Int, size: Int): List<Shop>

    // 模拟Item的【增删改】
    suspend fun addItem(id: Int)
    suspend fun deleteItem(id: Int)
    suspend fun updateItemTitle(id: Int, title: String)

    // 模拟Item-Item的（顶部按钮列表）【增删改】
    suspend fun addButtonItem(id: Int)
    suspend fun deleteButtonItem(id: Int)
    suspend fun updateButtonItemTitle(id: Int, title: String)
}