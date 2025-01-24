package com.bytedance.douyin.core.common.interfaces

/**
 * 描述：
 *
 * @author zhangrq
 * createTime 2024/3/11 上午11:28
 */
interface OnSwitchTabListener {
    /**
     * 切换tab，[index]为-1或者异常值，则切换到默认位置。
     */
    fun onSwitchTab(index: Int, smoothScroll: Boolean = false)
}