package com.bytedance.douyin.core.architecture.util

import androidx.viewpager2.widget.ViewPager2
import com.bytedance.core.architecture.base.views.adapter.BaseFragmentStateAdapter
import com.bytedance.core.model.BaseFragmentStateDiffItem
import com.bytedance.core.common.util.asUnsafe

/**
 * 描述：
 *
 * @author zhangrq
 * createTime 2024/4/1 上午11:22
 */

/**
 * 设置[list]数据和[createAdapter]创建的Adapter。
 * [offscreenPageLimit]设置预加载数量，null或小于等于0的值为无预加载、[Int.MAX_VALUE]为预加载全部、其他值为固定预加载数量。
 * 说明：使用此方法是为了解决，因为配置变更而导致的页面销毁后的还原（如：ViewPager2原来的位置等）问题，
 * ViewPager2会在setAdapter()的时候进行上次的还原，如果以先setAdapter()，后再全部通过拿到数据之后再adapter.setData()进行刷新数据，则此ViewPager2的还原则会失效。
 */
fun <T : BaseFragmentStateDiffItem> ViewPager2.setDataOrAdapter(
    list: List<T>?,
    offscreenPageLimit: Int?,
    createAdapter: () -> BaseFragmentStateAdapter<T>,
) {
    // 设置预加载
    setOffscreenPageLimit(
        if (offscreenPageLimit == null || offscreenPageLimit <= 0) {
            // 小值，用默认的。
            ViewPager2.OFFSCREEN_PAGE_LIMIT_DEFAULT
        } else if (offscreenPageLimit == Int.MAX_VALUE) {
            // 大值，用全部的。
            val itemCount = (list?.size ?: 0)
            if (itemCount >= 2) {
                // 2个及以上
                itemCount - 1
            } else {
                // 没有或者仅有1个，用默认的
                ViewPager2.OFFSCREEN_PAGE_LIMIT_DEFAULT
            }
        } else {
            // 中间值，用设置的值。
            offscreenPageLimit
        }
    )

    if (adapter == null) {
        // create adapter
        if (list.isNullOrEmpty()) return // 初始化状态，不设置下面的，防止没恢复之前保持的数据。不能使用adapter.setStateRestorationPolicy(PREVENT_WHEN_EMPTY)来替代实现，因为此ViewPager2是通过setAdapter进行恢复的。
        val fragmentStateAdapter = createAdapter()
        fragmentStateAdapter.submitList(list)
        // set adapter
        adapter = fragmentStateAdapter
    } else {
        // update adapter
        adapter.asUnsafe<BaseFragmentStateAdapter<T>>().submitList(list)
    }
}