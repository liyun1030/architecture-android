package com.bytedance.douyin.core.designsystem.interfaces.empty

import android.view.View
import androidx.drawerlayout.widget.DrawerLayout

/**
 * 描述：
 *
 * @author zhangrq
 * createTime 2024/4/1 上午11:13
 */
interface DefaultDrawerListener : DrawerLayout.DrawerListener {
    override fun onDrawerSlide(drawerView: View, slideOffset: Float) {
    }

    override fun onDrawerOpened(drawerView: View) {
    }

    override fun onDrawerClosed(drawerView: View) {
    }

    override fun onDrawerStateChanged(newState: Int) {
    }
}