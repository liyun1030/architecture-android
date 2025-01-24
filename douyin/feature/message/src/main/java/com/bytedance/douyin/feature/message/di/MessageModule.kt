package com.bytedance.douyin.feature.message.di

import com.bytedance.douyin.core.router.interfaces.MessageRouter
import com.bytedance.douyin.feature.message.DefaultMessageRouter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * 描述：
 *
 * @author zhangrq
 * createTime 2024/3/19 下午5:11
 */
@Module
@InstallIn(SingletonComponent::class)
object MessageModule {

    @Provides
    @Singleton
    fun providesMessageRouter(): MessageRouter = DefaultMessageRouter()
}