package com.bytedance.douyin.core.data.repository

import com.bytedance.douyin.core.common.network.di.ApplicationScope
import com.bytedance.douyin.core.data.repository.interfaces.HomeRepository
import com.bytedance.douyin.core.data.repository.interfaces.HomeTabsSortRepository
import com.bytedance.douyin.core.datastore.AppPreferencesDataSource
import com.bytedance.douyin.core.model.HomeTabType
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * 描述：
 *
 * @author zhangrq
 * createTime 2024/10/25 09:58
 */
class DefaultHomeTabsSortRepository @Inject constructor(
    homeRepository: HomeRepository,
    private val preferencesDataSource: AppPreferencesDataSource,
    @ApplicationScope private val appScope: CoroutineScope,
) : HomeTabsSortRepository {
    // 本地HomeTabsFlow
    private val localHomeTabsFlow = homeRepository.getHomeTabsStream().map {
        // 本地有修改，设置缓存值。
        val homeTabsReversed = it.asReversed()
        cachedHomeTabsFlow.value = homeTabsReversed
        homeTabsReversed
    }
    // 缓存HomeTabsFlow
    private val cachedHomeTabsFlow = MutableStateFlow(emptyList<HomeTabType>())

    override fun getHomeTabsCacheStream() =
        combine(localHomeTabsFlow, cachedHomeTabsFlow) { _, cachedHomeTabs -> cachedHomeTabs }

    override fun move(from: Int, to: Int) {
        cachedHomeTabsFlow.update { items ->
            val mutableItems = items.toMutableList()
            if (from in items.indices && to in items.indices) {
                val e = mutableItems.removeAt(from)
                mutableItems.add(to, e)
            }
            mutableItems
        }
    }

    override fun save() {
        // 使用全局的Scope保存，保证了不会因为Activity、Fragment销毁而取消。
        appScope.launch {
            preferencesDataSource.setHomeTabs(cachedHomeTabsFlow.value.asReversed())
        }
    }
}