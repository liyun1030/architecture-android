package com.bytedance.douyin.core.common.util

import android.app.Activity
import androidx.core.view.WindowInsetsControllerCompat
import androidx.fragment.app.Fragment
import com.bytedance.core.common.util.isDarkMode

/**
 * 描述：
 *
 * @author zhangrq
 * createTime 2024/12/18 16:11
 */

/**
 * 设置状态栏-字体颜色
 * [isDarkFont]为是否设置暗字体
 */
fun Fragment.setStatusBarDarkFont(isDarkFont: Boolean) = activity?.setStatusBarDarkFont(isDarkFont)

fun Activity.setStatusBarDarkFont(isDarkFont: Boolean) {
    // 根据值来，如果是暗模式则取反值。
    WindowInsetsControllerCompat(window, window.decorView).isAppearanceLightStatusBars =
        if (isDarkMode()) !isDarkFont else isDarkFont
}