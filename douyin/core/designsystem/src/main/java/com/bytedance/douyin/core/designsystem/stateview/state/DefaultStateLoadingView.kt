package com.bytedance.douyin.core.designsystem.stateview.state

import android.view.View
import androidx.core.view.isVisible
import com.bytedance.douyin.core.designsystem.R
import com.bytedance.douyin.core.designsystem.stateview.state.interfaces.StateLoadingView

/**
 * 描述：
 *
 * @author zhangrq
 * createTime 2024/12/11 14:03
 */
class DefaultStateLoadingView : ConstraintLayoutMergeStateBaseView(), StateLoadingView {
    private val loading by lazy { root!!.findViewById<View>(R.id.state_loading) }

    override fun getResId() = R.layout.douyin_core_designsystem_view_state_loading

    override fun setVisible(isVisible: Boolean) {
        loading.isVisible = isVisible
    }
}