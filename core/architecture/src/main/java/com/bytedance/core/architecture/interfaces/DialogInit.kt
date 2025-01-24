package com.bytedance.core.architecture.interfaces

import android.app.Dialog
import android.view.Window

/**
 * 描述：
 *
 * @author zhangrq
 * createTime 2024/10/24 10:34
 */
interface DialogInit {
    fun initDialog(dialog: Dialog)

    fun initDialogWindow(window: Window)
}