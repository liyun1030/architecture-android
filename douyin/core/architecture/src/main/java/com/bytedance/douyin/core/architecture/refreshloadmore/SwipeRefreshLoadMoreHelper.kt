package com.bytedance.douyin.core.architecture.refreshloadmore

import android.view.ViewGroup
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.bytedance.core.designsystem.widget.interfaces.StateView
import com.bytedance.douyin.core.data.repository.refreshloadmore.LoadState
import com.bytedance.douyin.core.architecture.refreshloadmore.base.BaseRefreshLoadMoreHelper
import com.chad.library.adapter4.BaseQuickAdapter

/**
 * 描述：[BaseRefreshLoadMoreHelper]-[SwipeRefreshLayout]实现
 *
 * @author zhangrq
 * createTime 2024/11/12 10:14
 */
class SwipeRefreshLoadMoreHelper(
    viewModel: ViewModel,
    recyclerView: RecyclerView,
    adapter: BaseQuickAdapter<*, *>,
    createStateView: ((ViewGroup) -> StateView)? = null,
) : BaseRefreshLoadMoreHelper<SwipeRefreshLayout>(
    viewModel,
    recyclerView,
    adapter,
    createStateView
) {

    override fun SwipeRefreshLayout.initViewRefreshEnable(refresh: () -> Unit) {
        isEnabled = true
        setOnRefreshListener {
            refresh()
        }
    }

    override fun SwipeRefreshLayout.isViewRefreshing() = isRefreshing

    override fun SwipeRefreshLayout.setViewRefreshState(
        loadState: LoadState?,
        enableRefresh: Boolean,
    ) {
        // 设置刷新状态
        // 刷新并且当前列表不为空（未展示stateView），展示刷新UI效果，其他不展示。
        isRefreshing =
            loadState?.isRefresh == true && loadState is LoadState.Loading && !loadState.isCurrentDisplayEmptyList

        // 设置是否能刷新
        isEnabled = enableRefresh
    }

}