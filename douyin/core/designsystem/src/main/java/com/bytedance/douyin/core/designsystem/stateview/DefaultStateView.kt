package com.bytedance.douyin.core.designsystem.stateview

import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import com.bytedance.core.designsystem.widget.interfaces.StateView
import com.bytedance.douyin.core.designsystem.stateview.state.DefaultStateEmptyView
import com.bytedance.douyin.core.designsystem.stateview.state.DefaultStateErrorView
import com.bytedance.douyin.core.designsystem.stateview.state.DefaultStateLoadingView
import com.bytedance.douyin.core.designsystem.stateview.state.interfaces.StateBaseView
import com.bytedance.douyin.core.designsystem.stateview.state.interfaces.StateEmptyView
import com.bytedance.douyin.core.designsystem.stateview.state.interfaces.StateErrorView
import com.bytedance.douyin.core.designsystem.stateview.state.interfaces.StateLoadingView

/**
 * 描述：StateView-默认实现，可修改3个状态布局中的默认实现，以达到定制效果。
 * 也可以自己自定义实现[StateView]，以达到更深入的定制。
 * 说明：上面3个状态布局的实现，需要使用ConstraintLayout规则的merge实现，并且子控件Id名称不能重复。
 *
 * @author zhangrq
 * createTime 2024/11/13 14:20
 */
class DefaultStateView(
    val parent: ViewGroup,
    private val stateLoadingView: StateLoadingView = DefaultStateLoadingView(),
    private val stateErrorView: StateErrorView = DefaultStateErrorView(),
    private val stateEmptyView: StateEmptyView = DefaultStateEmptyView(),
) : StateView {

    // 上面3个实现，需要使用ConstraintLayout规则的merge实现，并且子控件Id名称不能重复。
    override val root: View = ConstraintLayout(parent.context).apply {
        layoutParams = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )
    }

    override fun showLoading() {
        setLayoutVisible(loading = true)
    }

    override fun showError(error: Throwable, retry: View.OnClickListener?) {
        setLayoutVisible(error = true)
        stateErrorView.onError(error, retry)
    }

    override fun showEmpty() {
        setLayoutVisible(empty = true)
    }

    private fun setLayoutVisible(
        loading: Boolean = false,
        error: Boolean = false,
        empty: Boolean = false,
    ) {
        val parent = root as ViewGroup
        // loading
        setBaseStateViewVisible(stateLoadingView, parent, loading)
        // error
        setBaseStateViewVisible(stateErrorView, parent, error)
        // empty
        setBaseStateViewVisible(stateEmptyView, parent, empty)
    }

    private fun setBaseStateViewVisible(
        stateBaseView: StateBaseView,
        parent: ViewGroup,
        isVisible: Boolean,
    ) {
        if (isVisible) {
            // 展示Loading，没有则会创建。
            if (stateBaseView.root == null) {
                stateBaseView.root = stateBaseView.createRootView(parent)
            }
            stateBaseView.setVisible(true)
        } else {
            // 隐藏Loading，没有则不操作。
            if (stateBaseView.root != null) {
                stateBaseView.setVisible(false)
            }
        }
    }
}