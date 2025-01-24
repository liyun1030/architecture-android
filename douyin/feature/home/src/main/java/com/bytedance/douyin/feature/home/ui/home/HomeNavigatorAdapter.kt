package com.bytedance.douyin.feature.home.ui.home

import android.content.Context
import android.graphics.Typeface
import android.util.TypedValue
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.viewpager2.widget.ViewPager2
import com.bytedance.core.common.util.asSafe
import com.bytedance.core.common.util.getDataFromThemeAttr
import com.bytedance.douyin.core.architecture.app.views.adapter.AppNavigatorAdapter
import com.bytedance.douyin.core.designsystem.widget.NoSetColorSimplePagerTitleView
import com.bytedance.douyin.feature.home.R
import com.bytedance.douyin.feature.home.ui.hometabssort.HomeTabsSortDialogFragment
import net.lucode.hackware.magicindicator.MagicIndicator
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.LinePagerIndicator
import com.bytedance.douyin.core.designsystem.R as designSystemR
import com.google.android.material.R as materialR
import net.lucode.hackware.magicindicator.R as magicIndicatorR


/**
 * 描述：
 *
 * @author zhangrq
 * createTime 2024/4/1 上午9:14
 */
class HomeNavigatorAdapter(
    private val viewPager2: ViewPager2,
    private val magicIndicator: MagicIndicator,
    private val fragmentManager: FragmentManager,
) : AppNavigatorAdapter<HomeTabsItem>() {

    private var isDarkFont: Boolean = true

    override fun getTitleView(context: Context, index: Int, item: HomeTabsItem): IPagerTitleView {
        val simplePagerTitleView = NoSetColorSimplePagerTitleView(context)
        simplePagerTitleView.setText(item.titleResId)
        simplePagerTitleView.setTextSize(
            TypedValue.COMPLEX_UNIT_PX,
            context.resources.getDimension(R.dimen.douyin_feature_home_magic_indicator_title_text_size)
        )
        // 设置颜色，在此设置是为了解决跳转到后台或其他页面，然后再回来，会重新设置Adapter的数据，内部调用了notifyDataSetChanged()，所以此布局会重新创建一次。
        simplePagerTitleView.setColor(isDarkFont, index == viewPager2.currentItem)
        simplePagerTitleView.setTypeface(null, Typeface.BOLD)

        simplePagerTitleView.setOnClickListener { viewPager2.setCurrentItem(index, false) }
        simplePagerTitleView.setOnLongClickListener {
            // 展示排序Dialog
            HomeTabsSortDialogFragment().show(fragmentManager, null)
            true
        }
        return simplePagerTitleView
    }

    override fun getIndicator(context: Context): IPagerIndicator {
        val linePagerIndicator = LinePagerIndicator(context)
        linePagerIndicator.mode = LinePagerIndicator.MODE_EXACTLY
        // 设置颜色，原因同getTitleView。
        linePagerIndicator.setColor(isDarkFont)
        linePagerIndicator.lineWidth =
            context.resources.getDimension(designSystemR.dimen.douyin_core_designsystem_magic_indicator_indicator_line_width)
        return linePagerIndicator
    }

    fun setIsDarkFont(isDarkFont: Boolean) {
        this.isDarkFont = isDarkFont
        // 说明：使用MagicIndicator提供的只能实现更改变化的2个（进入、离开），而每次使用notifyDataSetChanged()创建创建又会出现卡顿，
        // -所以在此进行重新自定义，拿到原来的View然后进行设置更新。

        // 设置标题颜色
        setTitleColor(isDarkFont)
        // 设置指示器颜色
        setIndicatorColor(isDarkFont)
    }

    private fun setTitleColor(isDarkFont: Boolean) {
        val titleContainer =
            magicIndicator.navigator.asSafe<CommonNavigator>()?.titleContainer ?: return
        for (i in 0 until titleContainer.childCount) {
            // item view
            titleContainer.getChildAt(i).asSafe<NoSetColorSimplePagerTitleView>()
                ?.setColor(isDarkFont, i == viewPager2.currentItem)
        }
    }

    private fun setIndicatorColor(isDarkFont: Boolean) {
        val indicatorContainer = magicIndicator.navigator.asSafe<ViewGroup>()
            ?.findViewById<ViewGroup>(magicIndicatorR.id.indicator_container) ?: return
        val linePagerIndicator = indicatorContainer.getChildAt(0).asSafe<LinePagerIndicator>()
        linePagerIndicator?.setColor(isDarkFont)
        // 通知指示器绘制
        linePagerIndicator?.onPageScrolled(viewPager2.currentItem, 0f, 0)
    }

    private fun LinePagerIndicator.setColor(
        isDarkFont: Boolean,
    ) {
        val indicatorColor =
            magicIndicator.context.getDataFromThemeAttr(if (isDarkFont) materialR.attr.colorOnSurface else materialR.attr.colorOnSurfaceInverse)
        // 设置颜色
        setColors(indicatorColor)
    }

    private fun NoSetColorSimplePagerTitleView.setColor(
        isDarkFont: Boolean,
        selected: Boolean,
    ) {
        // 设置值
        alpha = if (selected) 1f else 0.7f
        setTextColor(context.getDataFromThemeAttr(if (isDarkFont) materialR.attr.colorOnSurface else materialR.attr.colorOnSurfaceInverse))
    }
}