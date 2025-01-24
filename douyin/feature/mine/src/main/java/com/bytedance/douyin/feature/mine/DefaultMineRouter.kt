package com.bytedance.douyin.feature.mine

import androidx.fragment.app.Fragment
import com.bytedance.douyin.core.router.interfaces.MineRouter
import com.bytedance.douyin.feature.mine.ui.MineFragment

/**
 * 描述：
 *
 * @author zhangrq
 * createTime 2024/3/19 下午5:15
 */
class DefaultMineRouter : MineRouter {
    override fun createMineFragment(): Fragment = MineFragment.newInstance()
}