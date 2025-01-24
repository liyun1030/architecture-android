package com.bytedance.douyin.core.router.interfaces.shop


/**
 * 描述：
 *
 * @author zhangrq
 * createTime 2024/12/4 15:20
 */
@Suppress("PropertyName")
interface ShopRouterContract {
    val Order: OrderRouter
    val Shop: ShopRouter
}