package com.bytedance.douyin.core.designsystem.stateview.state.interfaces

import android.view.View
import android.view.ViewGroup

/**
 * 描述：
 *
 * @author zhangrq
 * createTime 2024/12/11 14:55
 */
interface StateBaseView {
    var root: View?
    fun createRootView(parent: ViewGroup): View

    // 设置当前控件的所有布局显示或者隐藏
    fun setVisible(isVisible: Boolean)
}