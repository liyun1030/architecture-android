package com.bytedance.core.designsystem.widget.interfaces

import android.view.View

/**
 * 描述：
 *
 * @author zhangrq
 * createTime 2024/11/11 10:57
 */
interface StateView {
    val root: View

    fun showLoading()
    fun showError(error: Throwable, retry: View.OnClickListener?)
    fun showEmpty()
}