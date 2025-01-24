package com.bytedance.douyin.core.architecture.refreshloadmore

import android.view.ViewGroup
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.RecyclerView
import com.bytedance.core.designsystem.widget.interfaces.StateView
import com.bytedance.douyin.core.architecture.refreshloadmore.base.BaseRefreshLoadMoreHelper
import com.bytedance.douyin.core.data.repository.refreshloadmore.LoadState
import com.chad.library.adapter4.BaseQuickAdapter
import com.scwang.smart.refresh.layout.SmartRefreshLayout

/**
 * 描述：[BaseRefreshLoadMoreHelper]-[SmartRefreshLayout]实现
 *
 * @author zhangrq
 * createTime 2024/11/12 10:14
 */
class SmartRefreshLoadMoreHelper(
    viewModel: ViewModel,
    recyclerView: RecyclerView,
    adapter: BaseQuickAdapter<*, *>,
    createStateView: ((ViewGroup) -> StateView)? = null,
) : BaseRefreshLoadMoreHelper<SmartRefreshLayout>(
    viewModel,
    recyclerView,
    adapter,
    createStateView
) {
    private var function: Runnable? = null

    override fun SmartRefreshLayout.initViewRefreshEnable(refresh: () -> Unit) {
        setEnableRefresh(true)
        setEnableLoadMore(false)
        setOnRefreshListener {
            refresh()
        }
    }

    override fun SmartRefreshLayout.isViewRefreshing() = isRefreshing

    override fun SmartRefreshLayout.setViewRefreshState(
        loadState: LoadState?,
        enableRefresh: Boolean,
    ) {
        // 设置刷新状态
        if (loadState?.isRefresh == true) {
            // 刷新下，展示UI和设置结果，其它不操作。
            when (loadState) {
                is LoadState.Loading -> {
                    if (!loadState.isCurrentDisplayEmptyList) {
                        // 刷新并且当前列表不为空（未展示stateView），展示刷新UI效果。
                        autoRefreshAnimationOnly()
                    }
                }

                is LoadState.Error -> {
                    finishRefresh(0, false, false)
                }

                is LoadState.Success -> {
                    finishRefresh(0, true, loadState.isNoMoreData)
                }
            }
        }
        // 设置是否能刷新
        removeCallbacks(function) // 先移除原来的
        if (loadState?.isRefresh == true && !enableRefresh) {
            // 刷新并且要设置为不能刷新，做延迟设置，目的为了解决上面刷新时设置的刷新动画（开始、完成），如果立即设置为不能刷新，会导致上面刷新动画显示错误。
            function = Runnable { setEnableRefresh(false) }
            postDelayed(function, 300)
        } else {
            setEnableRefresh(enableRefresh)
        }

    }
}