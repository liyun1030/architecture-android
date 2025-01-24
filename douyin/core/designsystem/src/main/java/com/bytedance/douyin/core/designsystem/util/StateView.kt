package com.bytedance.douyin.core.designsystem.util

import android.view.ViewGroup
import com.bytedance.douyin.core.designsystem.stateview.DefaultStateView

/**
 * 描述：
 *
 * @author zhangrq
 * createTime 2024/12/19 17:58
 */
/**
 * 创建StateView-Activity、Fragment、DialogFragment的
 */
fun createAppStateView(parent: ViewGroup) = DefaultStateView(parent)

/**
 * 创建StateView-列表的
 */
fun createAppListStateView(parent: ViewGroup) = DefaultStateView(parent)