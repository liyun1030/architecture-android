package com.bytedance.douyin.core.router

import com.bytedance.douyin.core.router.fake.FakeHomeRouter
import com.bytedance.douyin.core.router.fake.FakeLoginRouter
import com.bytedance.douyin.core.router.fake.FakeMessageRouter
import com.bytedance.douyin.core.router.fake.FakeMineRouter
import com.bytedance.douyin.core.router.fake.shop.FakeOrderRouter
import com.bytedance.douyin.core.router.fake.shop.FakeShopRouter
import com.bytedance.douyin.core.router.fake.FakeVideoRouter
import com.bytedance.douyin.core.router.interfaces.HomeRouter
import com.bytedance.douyin.core.router.interfaces.LoginRouter
import com.bytedance.douyin.core.router.interfaces.MessageRouter
import com.bytedance.douyin.core.router.interfaces.MineRouter
import com.bytedance.douyin.core.router.interfaces.RouterContract
import com.bytedance.douyin.core.router.interfaces.VideoRouter
import com.bytedance.douyin.core.router.interfaces.shop.OrderRouter
import com.bytedance.douyin.core.router.interfaces.shop.ShopRouter
import com.bytedance.douyin.core.router.interfaces.shop.ShopRouterContract

/**
 * 描述：
 *
 * @author zhangrq
 * createTime 2024/3/19 下午5:00
 */
object Router : RouterContract {

    override val Home: HomeRouter by lazy { homeRouter() }
    override val Message: MessageRouter by lazy { messageRouter() }
    override val Mine: MineRouter by lazy { mineRouter() }
    override val Video: VideoRouter by lazy { videoRouter() }
    override val Login: LoginRouter by lazy { loginRouter() }

    // 商城
    object Shop : ShopRouterContract {
        override val Order: OrderRouter by lazy { orderRouter() }
        override val Shop: ShopRouter by lazy { shopRouter() }
    }
}

private fun homeRouter(): HomeRouter =
    provideRouter("com.bytedance.douyin.feature.home.DefaultHomeRouter") { FakeHomeRouter() }

private fun messageRouter(): MessageRouter =
    provideRouter("com.bytedance.douyin.feature.message.DefaultMessageRouter") { FakeMessageRouter() }

private fun mineRouter(): MineRouter =
    provideRouter("com.bytedance.douyin.feature.mine.DefaultMineRouter") { FakeMineRouter() }

private fun videoRouter(): VideoRouter =
    provideRouter("com.bytedance.douyin.feature.video.DefaultVideoRouter") { FakeVideoRouter() }

private fun loginRouter(): LoginRouter =
    provideRouter("com.bytedance.douyin.feature.login.DefaultLoginRouter") { FakeLoginRouter() }

// 商城
private fun orderRouter(): OrderRouter =
    provideRouter("com.bytedance.douyin.shop.feature.order.DefaultOrderRouter") { FakeOrderRouter() }

private fun shopRouter(): ShopRouter =
    provideRouter("com.bytedance.douyin.shop.feature.shop.DefaultShopRouter") { FakeShopRouter() }


@Suppress("UNCHECKED_CAST")
private fun <T> provideRouter(defaultRouterClassName: String, fakeRouter: () -> T): T {
    return try {
        Class.forName(defaultRouterClassName).getDeclaredConstructor().newInstance() as T
    } catch (e: Exception) {
        fakeRouter()
    }
}