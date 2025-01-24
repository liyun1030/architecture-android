package com.bytedance.douyin.feature.video.di

import com.bytedance.douyin.core.router.interfaces.VideoRouter
import com.bytedance.douyin.feature.video.DefaultVideoRouter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * 描述：
 *
 * @author zhangrq
 * createTime 2024/4/1 下午5:11
 */
@Module
@InstallIn(SingletonComponent::class)
// 单例，提供HomeRouter。
object VideoModule {

    @Provides
    @Singleton
    fun providesVideoRouter(): VideoRouter = DefaultVideoRouter()
}