package com.bytedance.douyin.core.data.repository.interfaces

import com.bytedance.douyin.core.model.MainTabType
import kotlinx.coroutines.flow.Flow

/**
 * 描述：
 *
 * @author zhangrq
 * createTime 2024/8/23 上午10:56
 */
interface MainRepository {
    fun getMainTabsStream(): Flow<List<MainTabType>>

    suspend fun updateMainTabShopOrFriend(isShop: Boolean)

}