package com.bytedance.douyin.shop.feature.shop.di

import com.bytedance.douyin.core.router.interfaces.shop.ShopRouter
import com.bytedance.douyin.shop.feature.shop.DefaultShopRouter
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
object ShopModule {

    @Provides
    @Singleton
    fun providesShopRouter(): ShopRouter = DefaultShopRouter()
}