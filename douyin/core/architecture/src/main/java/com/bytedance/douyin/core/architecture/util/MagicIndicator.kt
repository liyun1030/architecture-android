package com.bytedance.douyin.core.architecture.util

import android.view.animation.OvershootInterpolator
import androidx.viewpager2.widget.ViewPager2
import com.bytedance.core.common.util.asSafe
import com.bytedance.core.common.util.asUnsafe
import com.bytedance.douyin.core.architecture.app.views.adapter.AppNavigatorAdapter
import net.lucode.hackware.magicindicator.FragmentContainerHelper
import net.lucode.hackware.magicindicator.MagicIndicator
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator

/**
 * 描述：
 *
 * @author zhangrq
 * createTime 2024/3/5 上午11:21
 */
fun MagicIndicator.bindViewPager2(
    viewPager2: ViewPager2,
    useFragmentContainerHelper: Boolean = false,
    fragmentContainerHelperConfig: (FragmentContainerHelper.() -> Unit)? = {
        setInterpolator(OvershootInterpolator(1.0f))
        setDuration(200)
    },
) = apply {
    var fragmentContainerHelper: FragmentContainerHelper? = null
    if (useFragmentContainerHelper) {
        fragmentContainerHelper = FragmentContainerHelper(this)
        fragmentContainerHelperConfig?.invoke(fragmentContainerHelper)
    }

    viewPager2.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
        override fun onPageScrolled(
            position: Int,
            positionOffset: Float,
            positionOffsetPixels: Int,
        ) {
            super.onPageScrolled(position, positionOffset, positionOffsetPixels)
            if (!useFragmentContainerHelper) {
                // 用ViewPagerHelper的处理
                this@bindViewPager2.onPageScrolled(position, positionOffset, positionOffsetPixels)
            }
        }

        override fun onPageSelected(position: Int) {
            super.onPageSelected(position)
            if (!useFragmentContainerHelper) {
                // 用ViewPagerHelper的处理
                this@bindViewPager2.onPageSelected(position)
                // -兼容ViewPager2.setCurrentItem(index, false)不快速切换时，没有ViewPager那样触发onPageScrolled。
                this@bindViewPager2.onPageScrolled(position, 0f, 0)
            } else {
                // 用FragmentContainerHelper的处理
                fragmentContainerHelper?.handlePageSelected(position)
            }
        }

        override fun onPageScrollStateChanged(state: Int) {
            super.onPageScrollStateChanged(state)
            if (!useFragmentContainerHelper) {
                // 用ViewPagerHelper的处理
                this@bindViewPager2.onPageScrollStateChanged(state)
            }
        }
    })
}

/**
 * 设置CommonNavigator，用于给MagicIndicator设置特定的CommonNavigator，如果设置默认的，可以直接使用[setDataOrAdapter]。
 * [commonNavigatorInit]用于初始化CommonNavigator。
 */
fun MagicIndicator.setCommonNavigator(
    commonNavigatorInit: (CommonNavigator.() -> Unit)? = null,
) =
    CommonNavigator(context).apply {
        commonNavigatorInit?.invoke(this)
        setNavigator(this)
    }

/**
 * 设置[list]数据、[createNavigatorAdapter]创建的Adapter。
 * 说明：MagicIndicator内部没实现因为配置变更而导致的页面销毁后的还原操作，但是通过和ViewPager2的绑定，则也能间接性的进行还原操作。
 * 此实现仿ViewPager2，可以统一调用。
 */
fun <T> MagicIndicator.setDataOrAdapter(
    list: List<T>?,
    createNavigatorAdapter: () -> AppNavigatorAdapter<T>,
) {
    var commonNavigator = navigator.asSafe<CommonNavigator>()
    if (commonNavigator == null) {
        commonNavigator = CommonNavigator(context)
        setNavigator(commonNavigator)
    }
    val commonNavigatorAdapter = commonNavigator.adapter
    if (commonNavigatorAdapter == null) {
        // set adapter
        val adapter = createNavigatorAdapter().apply { submitList(list) }
        commonNavigator.adapter = adapter
    } else {
        // update adapter
        commonNavigatorAdapter.asUnsafe<AppNavigatorAdapter<T>>().submitList(list)
    }
}