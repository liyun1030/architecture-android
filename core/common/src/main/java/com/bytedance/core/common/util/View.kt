package com.bytedance.core.common.util

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import android.view.View
import android.view.ViewGroup.MarginLayoutParams

/**
 * 描述：
 *
 * @author zhangrq
 * createTime 2024/9/2 上午9:18
 */
/**
 * 获取view的ActivityContext
 */
fun View.activityContext(): Context? = when (val context = context) {
    is Activity -> context // 先判断这个Activity后判断ContextWrapper，因为Activity是ContextWrapper的子类。
    is ContextWrapper -> context.baseContext // 兼容Fragment使用了AndroidEntryPoint后，View的context是FragmentContextWrapper。
    else -> context
}

/**
 * 更新View的Margin，如果不是MarginLayoutParams则会报错。
 */
fun View.updateMargin(
    left: Int? = null,
    top: Int? = null,
    right: Int? = null,
    bottom: Int? = null,
    start: Int? = null,
    end: Int? = null,
) {
    val params = layoutParams as MarginLayoutParams
    with(params) {
        setMargins(
            left ?: leftMargin,
            top ?: topMargin,
            right ?: rightMargin,
            bottom ?: bottomMargin
        )
        marginStart = start ?: marginStart
        marginEnd = end ?: marginEnd
    }
    layoutParams = params
}