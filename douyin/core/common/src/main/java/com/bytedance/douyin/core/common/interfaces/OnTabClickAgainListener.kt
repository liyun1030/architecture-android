package com.bytedance.douyin.core.common.interfaces

/**
 * 描述：Main-Tab点击的监听
 *
 * @author zhangrq
 * createTime 2024/8/21 下午5:11
 */
// Tab点击-刷新
interface OnTabClickRefreshListener {
    fun onTabClickRefresh(listener: OnTabClickRefreshFinishListener)
}

interface OnTabClickRefreshFinishListener {
    fun onTabClickRefreshFinish()
}

// Tab点击-通用
@Suppress("EmptyMethod")
interface OnTabClickAgainListener {
    fun onTabClickAgain()
}