package com.bytedance.douyin.shop.feature.shop

import androidx.fragment.app.Fragment
import com.bytedance.douyin.core.router.interfaces.shop.ShopRouter
import com.bytedance.douyin.shop.feature.shop.ui.ShopFragment

/**
 * 描述：
 *
 * @author zhangrq
 * createTime 2024/3/19 下午5:15
 */
class DefaultShopRouter : ShopRouter {
    override fun createShopFragment(): Fragment = ShopFragment.newInstance()
}