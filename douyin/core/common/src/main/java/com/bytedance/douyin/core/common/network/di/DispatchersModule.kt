package com.bytedance.douyin.core.common.network.di

import com.bytedance.douyin.core.common.network.Dispatcher
import com.bytedance.douyin.core.common.network.AppDispatchers
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

/**
 * 描述：
 *
 * @author zhangrq
 * createTime 2024/3/13 上午10:48
 */
@Module
@InstallIn(SingletonComponent::class)
// 单例，提供Dispatcher(IO)、Dispatcher(Default)的CoroutineDispatcher。
object DispatchersModule {
    @Provides
    @Dispatcher(AppDispatchers.IO)
    // 提供IO的Dispatcher，使用Dispatchers.IO。
    fun providesIODispatcher(): CoroutineDispatcher = Dispatchers.IO

    @Provides
    @Dispatcher(AppDispatchers.Default)
    // 提供Default的Dispatcher，使用Dispatchers.Default。
    fun providesDefaultDispatcher(): CoroutineDispatcher = Dispatchers.Default
}
