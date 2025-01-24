package com.bytedance.douyin.core.data.repository.interfaces

import com.bytedance.douyin.core.model.HomeTabType
import kotlinx.coroutines.flow.Flow

/**
 * 描述：
 *
 * @author zhangrq
 * createTime 2024/8/23 上午10:57
 */
interface HomeTabsSortRepository {
    fun getHomeTabsCacheStream(): Flow<List<HomeTabType>>
    fun move(from: Int, to: Int)
    fun save()
}