package com.bytedance.core.designsystem.widget

import android.app.Activity
import android.app.Dialog
import android.widget.ProgressBar
import com.bytedance.core.designsystem.widget.interfaces.LoadingDialog

/**
 * 描述：
 *
 * @author zhangrq
 * createTime 2024/7/22 上午11:10
 */
class DefaultLoadingDialog private constructor(val activity: Activity) : Dialog(activity),
    LoadingDialog {

    override val dialog: Dialog = this

    init {
        setContentView(ProgressBar(context))
        window?.setBackgroundDrawable(null)
    }

    override fun showLoading() {
        if (activity.isFinishing || activity.isDestroyed || isShowing) {
            return
        }
        show()
    }

    override fun hideLoading() {
        if (activity.isFinishing || activity.isDestroyed || !isShowing) {
            return
        }
        dismiss()
    }

    companion object {
        fun create(activity: Activity) = DefaultLoadingDialog(activity)
    }
}