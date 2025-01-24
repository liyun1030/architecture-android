package com.bytedance.douyin.core.data.repository.refreshloadmore.interfaces

/**
 * 描述：
 *
 * @author zhangrq
 * createTime 2024/11/7 17:13
 */
interface OnRepositoryRefreshListener {

    fun refresh(finish: (() -> Unit)? = null)
}

interface OnRepositoryLoadMoreListener {

    fun load()

    fun loadRetry()
}