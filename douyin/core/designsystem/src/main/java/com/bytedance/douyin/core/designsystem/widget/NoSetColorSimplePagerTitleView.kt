package com.bytedance.douyin.core.designsystem.widget

import android.annotation.SuppressLint
import android.content.Context
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.SimplePagerTitleView

/**
 * 描述：带颜色渐变、缩放的，指示器标题。
 *
 * @author zhangrq
 * createTime 2024/3/4 下午4:55
 */
@SuppressLint("ViewConstructor")
class NoSetColorSimplePagerTitleView(
    context: Context,
) : SimplePagerTitleView(context) {

    override fun onSelected(index: Int, totalCount: Int) {
    }

    override fun onDeselected(index: Int, totalCount: Int) {
    }
}