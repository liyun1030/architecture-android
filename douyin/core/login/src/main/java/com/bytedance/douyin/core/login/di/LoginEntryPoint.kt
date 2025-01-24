package com.bytedance.douyin.core.login.di

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
 * createTime 2024/3/29 上午11:42
 */
@EntryPoint
@InstallIn(SingletonComponent::class)
interface LoginEntryPoint {
    fun appPreferencesDataSource(): AppPreferencesDataSource

    @ApplicationScope
    fun applicationScope(): CoroutineScope
}