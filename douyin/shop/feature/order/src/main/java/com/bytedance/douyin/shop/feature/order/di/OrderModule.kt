package com.bytedance.douyin.shop.feature.order.di

import com.bytedance.douyin.core.router.interfaces.shop.OrderRouter
import com.bytedance.douyin.shop.feature.order.DefaultOrderRouter
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
object OrderModule {

    @Provides
    @Singleton
    fun providesOrderRouter(): OrderRouter = DefaultOrderRouter()
}