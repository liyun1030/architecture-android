package com.bytedance.core.architecture.util

import android.os.Bundle
import androidx.fragment.app.Fragment

/**
 * 描述：
 *
 * @author zhangrq
 * createTime 2024/12/18 16:11
 */
private const val ARGUMENTS_KEY_IS_LAZY_INIT = "arguments_key_is_lazy_init"

/**
 * 设置Fragment是否是延迟初始化
 */
fun Fragment.setFragmentIsLazyInit(isFragmentLazyInit: Boolean) = apply {
    // 说明：由于Fragment配置变更后，会自动重新创建，所以只能通过arguments传递值。
    val arguments = arguments ?: Bundle()
    arguments.putBoolean(ARGUMENTS_KEY_IS_LAZY_INIT, isFragmentLazyInit)
    setArguments(arguments)
}

/**
 * 获取Fragment是否是延迟初始化
 */
fun Fragment.getFragmentIsLazyInit() = arguments?.getBoolean(ARGUMENTS_KEY_IS_LAZY_INIT) ?: false