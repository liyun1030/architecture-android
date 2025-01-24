package com.bytedance.douyin.core.router

import android.content.Context
import com.bytedance.douyin.core.router.di.RouterEntryPoint
import com.bytedance.douyin.core.router.di.ShopRouterEntryPoint
import com.bytedance.douyin.core.router.interfaces.HomeRouter
import com.bytedance.douyin.core.router.interfaces.LoginRouter
import com.bytedance.douyin.core.router.interfaces.MessageRouter
import com.bytedance.douyin.core.router.interfaces.MineRouter
import com.bytedance.douyin.core.router.interfaces.RouterContract
import com.bytedance.douyin.core.router.interfaces.VideoRouter
import com.bytedance.douyin.core.router.interfaces.shop.OrderRouter
import com.bytedance.douyin.core.router.interfaces.shop.ShopRouter
import com.bytedance.douyin.core.router.interfaces.shop.ShopRouterContract
import dagger.hilt.android.EntryPointAccessors

/**
 * 描述：
 *
 * @author zhangrq
 * createTime 2024/3/19 下午5:00
 */
object Router : RouterContract {
    private lateinit var routerEntryPoint: RouterEntryPoint
    private lateinit var shopRouterEntryPoint: ShopRouterEntryPoint

    fun init(context: Context) {
        routerEntryPoint =
            EntryPointAccessors.fromApplication(context, RouterEntryPoint::class.java)
        if (BuildConfig.isShopInclude) {
            shopRouterEntryPoint =
                EntryPointAccessors.fromApplication(context, ShopRouterEntryPoint::class.java)
        }
    }

    override val Home: HomeRouter by lazy { routerEntryPoint.homeRouter() }
    override val Message: MessageRouter by lazy { routerEntryPoint.messageRouter() }
    override val Mine: MineRouter by lazy { routerEntryPoint.mineRouter() }
    override val Video: VideoRouter by lazy { routerEntryPoint.videoRouter() }
    override val Login: LoginRouter by lazy { routerEntryPoint.loginRouter() }

    // 商城
    object Shop : ShopRouterContract {
        override val Order: OrderRouter by lazy { provideShopRouter { shopRouterEntryPoint.orderRouter() } }
        override val Shop: ShopRouter by lazy { provideShopRouter { shopRouterEntryPoint.shopRouter() } }
    }
}

private inline fun <reified T> provideShopRouter(router: () -> T): T {
    return if (BuildConfig.isShopInclude) {
        router()
    } else {
        throw RuntimeException("不包含商场模块，不能调用此[${T::class.simpleName}]。")
    }
}