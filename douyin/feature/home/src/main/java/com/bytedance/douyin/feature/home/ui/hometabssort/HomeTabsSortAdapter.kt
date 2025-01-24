package com.bytedance.douyin.feature.home.ui.hometabssort

import android.content.Context
import android.view.ViewGroup
import com.bytedance.douyin.core.architecture.app.views.adapter.AppAdapter
import com.bytedance.douyin.core.architecture.app.views.adapter.AppViewBindingViewHolder
import com.bytedance.douyin.feature.home.databinding.DouyinFeatureHomeItemHomeTabSortBinding as ViewBinding
import com.bytedance.douyin.feature.home.ui.hometabssort.HomeTabsSortItem as Item
import com.bytedance.douyin.feature.home.ui.hometabssort.HomeTabsSortViewHolder as ViewHolder

/**
 * 描述：
 *
 * @author zhangrq
 * createTime 2024/10/24 15:06
 */
class HomeTabsSortAdapter : AppAdapter<ViewBinding, Item, ViewHolder>() {

    override fun onCreateViewHolder(context: Context, parent: ViewGroup, viewType: Int) =
        ViewHolder(parent)
}

class HomeTabsSortViewHolder(parent: ViewGroup) :
    AppViewBindingViewHolder<ViewBinding, Item>(parent, ViewBinding::inflate) {

    override fun ViewBinding.bind(position: Int, item: Item) {
        // 是否可用，第一个不能，其他都能。
        title.isEnabled = position != 0
        // 标题
        title.setText(item.titleResId)
    }
}