package com.bytedance.douyin.core.data.repository

import com.bytedance.douyin.core.data.repository.interfaces.HomeRepository
import com.bytedance.douyin.core.datastore.AppPreferencesDataSource
import com.bytedance.douyin.core.model.HomeTabType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import javax.inject.Inject

/**
 * 描述：
 *
 * @author zhangrq
 * createTime 2024/8/22 上午9:50
 */
class DefaultHomeRepository @Inject constructor(
    private val preferencesDataSource: AppPreferencesDataSource,
) : HomeRepository {

    override fun getHomeTabsStream(): Flow<List<HomeTabType>> {
        return preferencesDataSource.userData.map { it.homeTabs }.distinctUntilChanged().map {
            if (it.isEmpty()) {
                // 为空，存入本地的。
                setDefaultHomeTabs()
            }
            it.map { item -> HomeTabType.entries[item] }
        }
    }

    private suspend fun setDefaultHomeTabs() {
        // 默认：热点、直播、精选、同城、团购、关注、经验、商城、推荐。
        val arrayListOf = arrayListOf(
            HomeTabType.HOT,
            HomeTabType.LIVE,
            HomeTabType.CHOICE,
            HomeTabType.SAME_CITY,
            HomeTabType.GROUP_BUYING,
            HomeTabType.FOLLOW,
            HomeTabType.EXPERIENCE,
            HomeTabType.SHOP,
            HomeTabType.RECOMMENDED,
        )
        preferencesDataSource.setHomeTabs(arrayListOf)
    }
}