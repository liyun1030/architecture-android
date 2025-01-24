package com.bytedance.core.architecture.stateview.util

import androidx.core.view.isInvisible
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import com.bytedance.core.architecture.base.BaseViewModel
import com.bytedance.core.architecture.stateview.LoadStateUiState
import com.bytedance.core.architecture.stateview.interfaces.DefaultStateViewManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

/**
 * 描述：
 *
 * @author zhangrq
 * createTime 2024/12/8 下午6:36
 */

/**
 * 初始化-加载状态UIState的收集
 */
fun DefaultStateViewManager.initLoadStateUiStateCollect(
    viewModel: BaseViewModel<*>,
    coroutineScope: CoroutineScope,
    lifecycle: Lifecycle,
) {
    coroutineScope.launch {
        viewModel.loadStateUiState
            .flowWithLifecycle(lifecycle)
            .collect { uiState ->
                collect(uiState)
            }
    }
}

private fun DefaultStateViewManager.collect(
    uiState: LoadStateUiState?,
) {
    when (uiState) {
        is LoadStateUiState.Loading -> if (uiState.isShowLoading) showLoading(uiState.isLoadingDialog) else hideLoading()
        is LoadStateUiState.Error -> if (uiState.isShowError) showErrorView(
            uiState.error,
            uiState.retry
        ) else hideLoading()

        is LoadStateUiState.Empty -> if (uiState.isShowEmpty) showEmptyView() else hideLoading()
        is LoadStateUiState.Success -> showSuccess()
        null -> {} // 为空，默认效果，不设置。
    }

    val isHideContent = uiState?.isHideContent ?: return // 为空，不设置。
    getStateViewReplaceView().isInvisible = isHideContent
}