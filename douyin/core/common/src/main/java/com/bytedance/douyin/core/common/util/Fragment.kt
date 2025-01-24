package com.bytedance.douyin.core.common.util

import android.os.Bundle
import androidx.fragment.app.Fragment

/**
 * 描述：
 *
 * @author zhangrq
 * createTime 2024/12/18 16:11
 */
const val ARGUMENTS_KEY_TOP_EXTRA_HEIGHT = "arguments_key_top_extra_height"

/**
 * 设置Fragment顶部额外高度
 */
fun Fragment.setFragmentTopExtraHeight(height: Int) = apply {
    // 说明：由于Fragment配置变更后，会自动重新创建，所以只能通过arguments传递值。
    val arguments = arguments ?: Bundle()
    arguments.putInt(ARGUMENTS_KEY_TOP_EXTRA_HEIGHT, height)
    setArguments(arguments)
}

/**
 * 获取Fragment顶部额外高度
 */
fun Fragment.getFragmentTopExtraHeight(): Int =
    arguments?.getInt(ARGUMENTS_KEY_TOP_EXTRA_HEIGHT) ?: 0