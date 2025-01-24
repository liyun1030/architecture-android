package com.bytedance.douyin.core.router.di

import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

/**
 * 描述：
 *
 * @author zhangrq
 * createTime 2024/3/29 上午11:42
 */
@EntryPoint
@InstallIn(SingletonComponent::class)
interface ShopRouterEntryPoint : IShopRouterEntryPoint