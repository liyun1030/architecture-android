package com.bytedance.douyin.app.main

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.bytedance.douyin.core.architecture.app.views.adapter.AppFragmentStateAdapter
import com.bytedance.douyin.core.model.MainTabType
import com.bytedance.douyin.core.router.Router

/**
 * 描述：
 *
 * @author zhangrq
 * createTime 2024/4/1 上午9:15
 */
class MainFragmentStateAdapter(activity: FragmentActivity) :
    AppFragmentStateAdapter<MainTabsItem>(activity, true) {

    override fun createFragment(position: Int, item: MainTabsItem): Fragment {
        return when (item.type) {
            MainTabType.HOME -> Router.Home.createHomeFragment()
            MainTabType.FRIEND -> Router.Video.createVideoFragment()
            MainTabType.CAMERA -> Fragment() // 占位Fragment
            MainTabType.MESSAGE -> Router.Message.createMessageFragment()
            MainTabType.MINE -> Router.Mine.createMineFragment()
            MainTabType.SHOP -> Router.Shop.Shop.createShopFragment()
        }
    }
}