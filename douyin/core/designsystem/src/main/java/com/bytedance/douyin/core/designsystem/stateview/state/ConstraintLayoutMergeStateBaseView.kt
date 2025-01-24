package com.bytedance.douyin.core.designsystem.stateview.state

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bytedance.douyin.core.designsystem.stateview.state.interfaces.StateBaseView

/**
 * 描述：BaseStateView-ConstraintLayout-merge实现
 * 说明：根布局为merge，不能使用ViewBinding实现，所以使用此来实现，内部的控件ID不能重复。
 *
 * @author zhangrq
 * createTime 2024/12/11 14:03
 */
abstract class ConstraintLayoutMergeStateBaseView : StateBaseView {
    override var root: View? = null
    override fun createRootView(parent: ViewGroup): View =
        LayoutInflater.from(parent.context).inflate(getResId(), parent, true)

    abstract fun getResId(): Int
}