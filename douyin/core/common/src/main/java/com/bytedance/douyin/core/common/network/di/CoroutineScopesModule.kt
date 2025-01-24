package com.bytedance.douyin.core.common.network.di

import com.bytedance.douyin.core.common.network.Dispatcher
import com.bytedance.douyin.core.common.network.AppDispatchers
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import javax.inject.Qualifier
import javax.inject.Singleton

/**
 * 描述：
 *
 * @author zhangrq
 * createTime 2024/3/13 上午10:40
 */
@Retention(AnnotationRetention.RUNTIME)
@Qualifier
// 全局协程作用域的限定符，用于标记使用全局协程作用域。
annotation class ApplicationScope

@Module
@InstallIn(SingletonComponent::class)
// 单例，提供ApplicationScope。
internal object CoroutineScopesModule {
    @Provides
    @Singleton
    @ApplicationScope
    // 提供全局协程作用域（ApplicationScope），单例。
    fun providesCoroutineScope(
        @Dispatcher(AppDispatchers.Default) dispatcher: CoroutineDispatcher,
        // 使用Dispatchers.Default+SupervisorJob，异常不会传播。
    ): CoroutineScope = CoroutineScope(SupervisorJob() + dispatcher)
}