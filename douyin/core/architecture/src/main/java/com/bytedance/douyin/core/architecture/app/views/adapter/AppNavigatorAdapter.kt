package com.bytedance.douyin.core.architecture.app.views.adapter

import android.content.Context
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView

/**
 * 描述：
 *
 * @author zhangrq
 * createTime 2024/4/1 上午8a:55
 */
abstract class AppNavigatorAdapter<T> : CommonNavigatorAdapter() {
    private var dataList: List<T> = emptyList()

    override fun getCount(): Int {
        return dataList.size
    }

    override fun getTitleView(context: Context, index: Int): IPagerTitleView? {
        return getTitleView(context, index, dataList[index])
    }

    abstract fun getTitleView(context: Context, index: Int, item: T): IPagerTitleView?

    override fun getIndicator(context: Context): IPagerIndicator? = null

    fun submitList(list: List<T>?) {
        if (dataList == list) return // 两个集合内容一样，说明没变化，不通知。
        dataList = list ?: emptyList()
        notifyDataSetChanged()
    }

    fun getItem(index: Int) = if (index < dataList.size) dataList[index] else null
}