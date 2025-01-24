package com.bytedance.core.architecture.stateview.util

import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams
import androidx.core.view.children
import com.bytedance.core.designsystem.widget.interfaces.StateView

/**
 * 描述：
 *
 * @author zhangrq
 * createTime 2024/12/8 上午11:00
 */
private const val TAG_STATE_VIEW = "tag_state_view"

fun ViewGroup.addStateView(
    stateView: StateView,
    layoutParams: LayoutParams? = null,
): StateView = stateView.apply {
    // 添加标记
    this.root.tag = TAG_STATE_VIEW
    // 添加View
    if (layoutParams == null) {
        this@addStateView.addView(this.root)
    } else {
        this@addStateView.addView(this.root, layoutParams)
    }
}

/**
 * 移除掉所有非StateView的View。
 */
fun ViewGroup.removeAllNoStateViewChildren() {
    children.forEach {
        if (TAG_STATE_VIEW != it.tag) {
            removeView(it)
        }
    }
}