package com.bytedance.douyin.core.data.repository

import com.bytedance.douyin.core.data.repository.interfaces.MainRepository
import com.bytedance.douyin.core.datastore.AppPreferencesDataSource
import com.bytedance.douyin.core.model.MainTabType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import javax.inject.Inject

/**
 * 描述：
 *
 * @author zhangrq
 * createTime 2024/3/1 下午2:15
 */
class DefaultMainRepository @Inject constructor(
    private val preferencesDataSource: AppPreferencesDataSource,
) : MainRepository {

    override fun getMainTabsStream(): Flow<List<MainTabType>> {
        // 首页、朋友、拍摄、消息、我的，由于[朋友]可以改为[商城]，所以返回值为Flow。
        return preferencesDataSource.userData.map { it.mainTabs }.distinctUntilChanged().map {
            if (it.isEmpty()) {
                // 为空，存入本地的。
                setDefaultMainTabs()
            }
            it.map { item -> MainTabType.entries[item] }
        }
    }

    private suspend fun setDefaultMainTabs() {
        // 默认：首页、朋友、拍摄、消息、我的
        val arrayListOf = arrayListOf(
            MainTabType.HOME,
            MainTabType.FRIEND,
            MainTabType.CAMERA,
            MainTabType.MESSAGE,
            MainTabType.MINE
        )
        preferencesDataSource.setMainTabs(arrayListOf)
    }

    override suspend fun updateMainTabShopOrFriend(isShop: Boolean) {
        preferencesDataSource.updateMainTabShopOrFriend(isShop)
    }
}