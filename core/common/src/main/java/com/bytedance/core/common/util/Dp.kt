package com.bytedance.core.common.util

import android.content.res.Resources
import android.util.TypedValue

/**
 * 描述：
 *
 * @author zhangrq
 * createTime 2024/6/28 下午3:50
 */
inline val Int.dp: Int
    get() = toFloat().toDp()

inline val Double.dp: Int
    get() = toFloat().toDp()

inline val Float.dp: Int
    get() = toDp()

fun Float.toDp() = TypedValue.applyDimension(
    TypedValue.COMPLEX_UNIT_DIP, this, Resources.getSystem().displayMetrics
).toInt()