package com.bytedance.core.designsystem.widget.interfaces

import android.app.Dialog

/**
 * 描述：
 *
 * @author zhangrq
 * createTime 2024/12/11 10:14
 */
interface LoadingDialog {
    val dialog: Dialog

    fun showLoading()

    fun hideLoading()
}