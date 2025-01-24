package com.bytedance.douyin.core.designsystem.util

import android.view.Gravity
import android.view.Window
import android.view.WindowManager
import com.bytedance.douyin.core.designsystem.R

/**
 * 描述：
 *
 * @author zhangrq
 * createTime 2024/10/28 11:26
 */
// 设置Dialog-Window底部样式动画
fun Window.setDialogWindowBottomStyle() = with(this) {
    // 设置动画
    setWindowAnimations(R.style.douyin_core_designsystem_window_animation_bottom)
    // 设置底部
    setGravity(Gravity.BOTTOM)

    // 设置width、height
    val params = attributes
    params.width = WindowManager.LayoutParams.MATCH_PARENT
    params.height = WindowManager.LayoutParams.WRAP_CONTENT
    attributes = params
}