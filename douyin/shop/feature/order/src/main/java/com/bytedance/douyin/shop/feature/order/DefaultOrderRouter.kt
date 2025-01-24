package com.bytedance.douyin.shop.feature.order

import android.app.Activity
import com.bytedance.douyin.core.router.interfaces.shop.OrderRouter
import com.bytedance.douyin.shop.feature.order.ui.OrderActivity

/**
 * 描述：
 *
 * @author zhangrq
 * createTime 2024/3/19 下午5:15
 */
class DefaultOrderRouter : OrderRouter {

    override fun startOrderActivity(activity: Activity) {
        OrderActivity.startActivity(activity)
    }
}