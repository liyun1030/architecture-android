package com.bytedance.douyin.core.data.repository.refreshloadmore

/**
 * 描述：
 *
 * @author zhangrq
 * createTime 2024/11/13 14:40
 */
sealed class LoadState(
    val isRefresh: Boolean,
    // 说明：由于是两个Flow，不知道那边setAdapter设置是否完成，所以不能通过adapter获取一些数据，所以得需要传递此LoadState需要的数据。
    val isCurrentDisplayEmptyList: Boolean,
) {
    class Loading(
        isRefresh: Boolean,
        isCurrentDisplayEmptyList: Boolean,
        // 当前显示的，是否是空列表。
    ) : LoadState(isRefresh, isCurrentDisplayEmptyList)

    class Success(
        isRefresh: Boolean,
        isCurrentDisplayEmptyList: Boolean,
        val isNoMoreData: Boolean,
    ) : LoadState(isRefresh, isCurrentDisplayEmptyList)

    class Error(
        isRefresh: Boolean,
        isCurrentDisplayEmptyList: Boolean,
        val error: Throwable,
    ) : LoadState(isRefresh, isCurrentDisplayEmptyList)
}

fun LoadState?.toMessageString() = when (this) {
    is LoadState.Error -> "Error(isRefresh=$isRefresh，isCurrentDisplayEmptyList=$isCurrentDisplayEmptyList，error=$error)"
    is LoadState.Loading -> "Loading(isRefresh=$isRefresh，isCurrentDisplayEmptyList=$isCurrentDisplayEmptyList)"
    is LoadState.Success -> "Success(isRefresh=$isRefresh，isCurrentDisplayEmptyList=$isCurrentDisplayEmptyList，isNoMoreData=$isNoMoreData)"
    null -> "null"
}