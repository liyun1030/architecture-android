package com.bytedance.douyin.feature

import com.bytedance.douyin.core.common.util.ARGUMENTS_KEY_TOP_EXTRA_HEIGHT
import com.bytedance.douyin.core.router.Router
import com.bytedance.douyin.shop.feature.shop.ui.ShopFragment
import com.zrq.test.point.annotation.TestEntryPointListFragment
import com.zrq.test.point.list.TestListFragment

/**
 * 描述：
 *
 * @author zhangrq
 * createTime 2024/4/18 下午4:10
 */
@TestEntryPointListFragment
class MyTestListFragment : TestListFragment() {

    override fun onAddTestItems() {
        super.onAddTestItems()
        addItem("商城-无参", ShopFragment::class.java)
        addItem("商城-有参", ShopFragment::class.java, ARGUMENTS_KEY_TOP_EXTRA_HEIGHT to 100)
        addItem("跳转Login页面-Router") {
            // 如果没有引用feature:login模块，则会打开假的占位页面。
            Router.Login.startLoginActivity(requireActivity())
        }
    }
}