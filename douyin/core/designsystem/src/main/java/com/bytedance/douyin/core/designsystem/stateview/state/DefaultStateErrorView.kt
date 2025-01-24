package com.bytedance.douyin.core.designsystem.stateview.state

import android.view.View
import android.widget.TextView
import androidx.core.view.isVisible
import com.bytedance.douyin.core.designsystem.R
import com.bytedance.douyin.core.designsystem.stateview.state.interfaces.StateErrorView
import com.bytedance.douyin.core.designsystem.util.toErrorMessage

/**
 * 描述：
 *
 * @author zhangrq
 * createTime 2024/12/11 14:03
 */
class DefaultStateErrorView : ConstraintLayoutMergeStateBaseView(), StateErrorView {
    private val errorGroup by lazy { root!!.findViewById<View>(R.id.state_error_group) }
    private val errorRetry by lazy { root!!.findViewById<View>(R.id.state_error_retry) }
    private val errorDescription by lazy { root!!.findViewById<TextView>(R.id.state_error_description) }

    override fun getResId() = R.layout.douyin_core_designsystem_view_state_error

    override fun setVisible(isVisible: Boolean) {
        errorGroup.isVisible = isVisible
    }

    override fun onError(error: Throwable, retry: View.OnClickListener?) {
        errorRetry.setOnClickListener(retry)
        errorDescription.text = error.toErrorMessage()
    }
}