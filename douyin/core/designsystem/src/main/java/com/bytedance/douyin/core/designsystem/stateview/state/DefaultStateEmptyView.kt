package com.bytedance.douyin.core.designsystem.stateview.state

import android.view.View
import androidx.core.view.isVisible
import com.bytedance.douyin.core.designsystem.R
import com.bytedance.douyin.core.designsystem.stateview.state.interfaces.StateEmptyView

/**
 * 描述：
 *
 * @author zhangrq
 * createTime 2024/12/11 14:03
 */
class DefaultStateEmptyView : ConstraintLayoutMergeStateBaseView(), StateEmptyView {

    private val emptyGroup by lazy { root!!.findViewById<View>(R.id.state_empty_group) }

    override fun getResId() = R.layout.douyin_core_designsystem_view_state_empty

    override fun setVisible(isVisible: Boolean) {
        emptyGroup.isVisible = isVisible
    }
}