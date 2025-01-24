package com.bytedance.core.common.util

import android.content.Context
import android.content.res.Configuration
import android.util.TypedValue

/**
 * 描述：
 *
 * @author zhangrq
 * createTime 2024/5/13 上午9:13
 */
fun Context.isDarkMode(): Boolean =
    resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK == Configuration.UI_MODE_NIGHT_YES

fun Context.getDataFromThemeAttr(attr: Int) = TypedValue().apply {
    theme.resolveAttribute(attr, this, true)
}.data