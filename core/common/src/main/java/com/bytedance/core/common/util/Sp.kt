package com.bytedance.core.common.util

import android.content.res.Resources
import android.util.TypedValue

/**
 * 描述：
 *
 * @author zhangrq
 * createTime 2024/6/28 下午3:50
 */

inline val Int.sp: Int
    get() = toFloat().toSp()

inline val Double.sp: Int
    get() = toFloat().toSp()

inline val Float.sp: Int
    get() = toSp()

fun Float.toSp() = TypedValue.applyDimension(
    TypedValue.COMPLEX_UNIT_SP, this, Resources.getSystem().displayMetrics
).toInt()