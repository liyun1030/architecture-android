package com.bytedance.douyin.feature.home

import androidx.fragment.app.Fragment
import com.bytedance.douyin.core.router.interfaces.HomeRouter
import com.bytedance.douyin.feature.home.ui.home.HomeFragment

/**
 * 描述：
 *
 * @author zhangrq
 * createTime 2024/3/19 下午5:15
 */
class DefaultHomeRouter : HomeRouter {
    override fun createHomeFragment(): Fragment = HomeFragment.newInstance()
}