package com.bytedance.douyin.shop.core.data.repository

import com.bytedance.douyin.core.data.repository.refreshloadmore.PageKeyedMemoryRefreshLoadMoreRepository
import com.bytedance.douyin.core.data.repository.refreshloadmore.util.addByItem
import com.bytedance.douyin.core.data.repository.refreshloadmore.util.deleteByItem
import com.bytedance.douyin.core.data.repository.refreshloadmore.util.updateByItem
import com.bytedance.douyin.shop.core.data.model.asExternalModel
import com.bytedance.douyin.shop.core.data.repository.interfaces.ShopRepository
import com.bytedance.douyin.shop.core.model.Shop
import com.bytedance.douyin.shop.core.model.ShopButton
import com.bytedance.douyin.shop.core.network.datasource.interfaces.NetworkShopDataSource
import javax.inject.Inject

/**
 * 描述：
 *
 * @author zhangrq
 * createTime 2024/9/5 下午5:17
 */
class DefaultShopRepository @Inject constructor(
    private val network: NetworkShopDataSource,
) : PageKeyedMemoryRefreshLoadMoreRepository<Shop>(), ShopRepository {

    override suspend fun getListDataByKey(key: Int, pageSize: Int): List<Shop> {
        return getShops(key, pageSize)
    }

    override suspend fun getShops(page: Int, size: Int): List<Shop> {
        return network.getShops(page, size).map { it.asExternalModel() }
    }

    override suspend fun addItem(id: Int) {
        val shop = network.addItem().asExternalModel()
        addByItem({ it.id == id }, shop)
    }

    override suspend fun deleteItem(id: Int) {
        network.deleteItem(id)
        deleteByItem { it.id == id }
    }

    override suspend fun updateItemTitle(id: Int, title: String) {
        network.updateItemTitle(id, title)
        updateByItem({ it.id == id }) {
            copy(title = title)
        }
    }

    override suspend fun addButtonItem(id: Int) {
        val shopButton = network.addButtonItem().asExternalModel()
        editButtonItem(id) { tempList, index ->
            tempList.add(index, shopButton)
        }
    }

    override suspend fun deleteButtonItem(id: Int) {
        network.deleteButtonItem(id)
        editButtonItem(id) { tempList, index ->
            tempList.removeAt(index)
        }
    }

    override suspend fun updateButtonItemTitle(id: Int, title: String) {
        network.updateButtonItemTitle(id, title)
        editButtonItem(id) { tempList, index ->
            val item = tempList[index]
            tempList[index] = item.copy(title = title)
        }
    }

    private fun editButtonItem(
        id: Int,
        action: (MutableList<ShopButton>, Int) -> Unit,
    ) {
        updateByItem({ it.type == 1 }) {
            val tempList = buttons?.toMutableList() ?: return@updateByItem this// 原来无数据，不操作，直接返回。
            val index = tempList.indexOfFirst { it.id == id }
            if (index == -1) return@updateByItem this// 没找到，不操作，直接返回。
            action(tempList, index)
            copy(buttons = tempList)
        }
    }
}