package com.bytedance.douyin.feature.home.di

import com.bytedance.douyin.core.router.interfaces.HomeRouter
import com.bytedance.douyin.feature.home.DefaultHomeRouter
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
// 单例，提供HomeRouter。
object HomeModule {

    @Provides
    @Singleton
    fun providesHomeRouter(): HomeRouter = DefaultHomeRouter()
}