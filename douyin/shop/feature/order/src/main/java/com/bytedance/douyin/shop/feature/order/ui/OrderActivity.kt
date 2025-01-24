package com.bytedance.douyin.shop.feature.order.ui

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import com.bytedance.douyin.core.architecture.app.views.AppViewsEmptyViewModelActivity
import com.zrq.test.point.annotation.TestEntryPoint
import dagger.hilt.android.AndroidEntryPoint
import com.bytedance.douyin.shop.feature.order.databinding.DouyinShopFeatureOrderActivityOrderBinding as ViewBinding

/**
 * 描述：
 *
 * @author zhangrq
 * createTime 2024/3/1 上午10:51
 */
@TestEntryPoint("订单")
@AndroidEntryPoint
class OrderActivity : AppViewsEmptyViewModelActivity<ViewBinding>() {

    override fun inflateViewBinding(inflater: LayoutInflater) = ViewBinding.inflate(inflater)

    override fun ViewBinding.initViews() {
    }

    override fun ViewBinding.initListeners() {
    }

    override fun ViewBinding.initObservers() {
    }

    companion object {

        internal fun startActivity(activity: Activity) {
            activity.startActivity(Intent(activity.applicationContext, OrderActivity::class.java))
        }
    }
}