package com.bytedance.douyin.core.architecture.app.views.adapter

import androidx.viewbinding.ViewBinding
import com.bytedance.core.model.BaseDiffItem

/**
 * 描述：RecyclerView的Adapter，内部使用Differ + ViewBinding实现，推荐使用。
 *
 * @author zhangrq
 * createTime 2024/11/21 10:11
 */
abstract class AppAdapter<Binding : ViewBinding, Item : BaseDiffItem, VH : AppViewBindingViewHolder<Binding, Item>> :
    AppDifferAdapter<Item, VH>() {
    override fun onBindViewHolder(holder: VH, position: Int, item: Item?) {
        // 由于没有实现Paging，所以Item不会为null。
        holder.bindData(position, item!!)
    }
}