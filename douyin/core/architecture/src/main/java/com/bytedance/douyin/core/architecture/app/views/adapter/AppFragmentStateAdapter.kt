package com.bytedance.douyin.core.architecture.app.views.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import com.bytedance.core.architecture.base.views.adapter.BaseFragmentStateAdapter
import com.bytedance.core.model.BaseFragmentStateDiffItem

/**
 * 描述：
 *
 * @author zhangrq
 * createTime 2024/4/1 上午9:46
 */
abstract class AppFragmentStateAdapter<T : BaseFragmentStateDiffItem>(
    fragmentManager: FragmentManager,
    lifecycle: Lifecycle,
    isFragmentLazyInit: Boolean,
) : BaseFragmentStateAdapter<T>(fragmentManager, lifecycle, isFragmentLazyInit) {
    constructor(
        activity: FragmentActivity,
        isFragmentLazyInit: Boolean,
    ) : this(activity.supportFragmentManager, activity.lifecycle, isFragmentLazyInit)

    constructor(
        fragment: Fragment, isFragmentLazyInit: Boolean,
    ) : this(fragment.childFragmentManager, fragment.lifecycle, isFragmentLazyInit)
}