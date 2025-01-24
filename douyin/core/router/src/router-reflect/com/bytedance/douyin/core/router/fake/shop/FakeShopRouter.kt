package com.bytedance.douyin.core.router.fake.shop

import androidx.fragment.app.Fragment
import com.bytedance.douyin.core.router.interfaces.shop.ShopRouter
import com.bytedance.douyin.core.test.AppTestFragment

/**
 * 描述：
 *
 * @author zhangrq
 * createTime 2024/3/19 下午5:15
 */
class FakeShopRouter : ShopRouter {

    override fun createShopFragment(): Fragment = AppTestFragment.newInstance("Shop")
}