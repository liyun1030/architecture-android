package com.bytedance.douyin.core.router.fake.shop

import android.app.Activity
import com.bytedance.douyin.core.router.interfaces.shop.OrderRouter
import com.bytedance.douyin.core.test.AppTestActivity

/**
 * 描述：
 *
 * @author zhangrq
 * createTime 2024/3/19 下午5:15
 */
class FakeOrderRouter : OrderRouter {

    override fun startOrderActivity(activity: Activity) {
        AppTestActivity.startActivity(activity, "OrderActivity")
    }
}