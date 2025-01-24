package com.bytedance.core.common.util

import android.app.Activity
import android.content.Context
import android.os.Build
import android.util.DisplayMetrics
import android.view.WindowManager

/**
 * 描述：
 *
 * @author zhangrq
 * createTime 2024/11/1 10:34
 */
/**
 * 获取-真正的-显示的-宽、高（没有排除任何的）
 */
@Suppress("DEPRECATION")
fun Context.getRealDisplaySize(): Pair<Int, Int> {
    val windowManager =
        getSystemService(Context.WINDOW_SERVICE) as WindowManager

    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
        // 高版本
        val windowMetrics = windowManager.maximumWindowMetrics
        val bounds = windowMetrics.bounds
        (bounds.width() to bounds.height())
    } else {
        // 低版本
        val dm = DisplayMetrics()
        windowManager.defaultDisplay?.getRealMetrics(dm)
        dm.widthPixels to dm.heightPixels
    }
}

/**
 * 获取-可用的-显示的-宽、高
 * 参数，必须用Activity，否则获取的不对。
 */
fun Activity.getAvailableDisplaySize(): Pair<Int, Int> {
    val metrics = resources.displayMetrics
    return metrics.widthPixels to metrics.heightPixels
}

fun getSpanCountByAvailableWidth(activity: Activity, minWidth: Int): Int {
    val availableWidth = activity.getAvailableDisplaySize().first
    return availableWidth / minWidth
}

fun getSpanCountByAvailableHeight(activity: Activity, minHeight: Int): Int {
    val availableHeight = activity.getAvailableDisplaySize().second
    return availableHeight / minHeight
}