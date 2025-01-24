package com.bytedance.core.architecture.base.views.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.recyclerview.widget.AdapterListUpdateCallback
import androidx.recyclerview.widget.AsyncDifferConfig
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.bytedance.core.architecture.util.setFragmentIsLazyInit
import com.bytedance.core.model.BaseFragmentStateDiffItem

/**
 * 描述：
 *
 * @author zhangrq
 * createTime 2024/4/1 上午9:46
 */
@Suppress("LeakingThis", "MemberVisibilityCanBePrivate")
abstract class BaseFragmentStateAdapter<T : BaseFragmentStateDiffItem>(
    private val fragmentManager: FragmentManager,
    lifecycle: Lifecycle,
    private val isFragmentLazyInit: Boolean,
) : FragmentStateAdapter(fragmentManager, lifecycle) {
    private var mDiffer: AsyncListDiffer<T> = AsyncListDiffer(
        AdapterListUpdateCallback(this),
        AsyncDifferConfig.Builder(BaseDiffItemCallback<T>()).build()
    )

    constructor(
        activity: FragmentActivity,
        isFragmentLazyInit: Boolean,
    ) : this(activity.supportFragmentManager, activity.lifecycle, isFragmentLazyInit)

    constructor(
        fragment: Fragment, isFragmentLazyInit: Boolean,
    ) : this(fragment.childFragmentManager, fragment.lifecycle, isFragmentLazyInit)

    override fun getItemCount(): Int {
        return mDiffer.currentList.size
    }

    override fun createFragment(position: Int): Fragment {
        return createFragment(position, getItem(position))
            // 设置Fragment是否是懒加载初始化
            .setFragmentIsLazyInit(isFragmentLazyInit)
    }

    // 返回对应位置的，数据项的唯一ID。
    override fun getItemId(position: Int): Long {
        return getItem(position).getItemId()
    }

    // 判断给定的itemId，是否在当前数据集中。
    override fun containsItem(itemId: Long): Boolean {
        return getCurrentList().any { it.getItemId() == itemId }
    }

    abstract fun createFragment(position: Int, item: T): Fragment

    fun submitList(list: List<T>?) {
        mDiffer.submitList(list)
    }

    // 文章：https://blog.csdn.net/cxz200367/article/details/115032032
    fun getFragmentByIndex(position: Int) =
        if (position in 0..<itemCount) fragmentManager.findFragmentByTag("f" + getItemId(position)) else null

    fun getCurrentList(): List<T> {
        return mDiffer.currentList
    }

    fun getItem(position: Int): T {
        return mDiffer.currentList[position]
    }
}