package com.bytedance.douyin.core.designsystem.di

import com.bytedance.douyin.core.common.network.di.ApplicationScope
import com.bytedance.douyin.core.datastore.AppPreferencesDataSource
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope

/**
 * 描述：
 *
 * @author zhangrq
 * createTime 2024/12/25 上午11:42
 */
@EntryPoint
@InstallIn(SingletonComponent::class)
interface DesignSystemEntryPoint {
    fun appPreferencesDataSource(): AppPreferencesDataSource

    @ApplicationScope
    fun applicationScope(): CoroutineScope
}