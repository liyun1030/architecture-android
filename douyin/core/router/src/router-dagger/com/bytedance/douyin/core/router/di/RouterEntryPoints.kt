package com.bytedance.douyin.core.router.di

import com.bytedance.douyin.core.router.interfaces.HomeRouter
import com.bytedance.douyin.core.router.interfaces.LoginRouter
import com.bytedance.douyin.core.router.interfaces.MessageRouter
import com.bytedance.douyin.core.router.interfaces.MineRouter
import com.bytedance.douyin.core.router.interfaces.shop.OrderRouter
import com.bytedance.douyin.core.router.interfaces.shop.ShopRouter
import com.bytedance.douyin.core.router.interfaces.VideoRouter
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
interface RouterEntryPoint {
    fun homeRouter(): HomeRouter
    fun messageRouter(): MessageRouter
    fun mineRouter(): MineRouter
    fun loginRouter(): LoginRouter
    fun videoRouter(): VideoRouter
}

//因为商场模块可以取消，所以取消后此类内部Router通过Dagger获取不到，因此改为通过源码动态控制其实现类。
interface IShopRouterEntryPoint {
    fun orderRouter(): OrderRouter
    fun shopRouter(): ShopRouter
}