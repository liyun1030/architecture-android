package com.bytedance.core.common.util

/**
 * 描述：
 *
 * @author zhangrq
 * createTime 2024/4/24 下午2:42
 */
inline fun <reified T> Any?.asSafe(): T? = this as? T

inline fun <reified T> Any?.asUnsafe(): T = this as T