package com.bytedance.core.architecture.base.views.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

/**
 * 描述：
 *
 * @author zhangrq
 * createTime 2024/10/24 15:21
 */
abstract class BaseViewBindingViewHolder<Binding : ViewBinding, Item>(val binding: Binding) :
    RecyclerView.ViewHolder(binding.root) {
    val context: Context get() = itemView.context

    constructor(
        parent: ViewGroup,
        bindingCreator: (LayoutInflater, ViewGroup?, Boolean) -> Binding,
    ) : this(bindingCreator(LayoutInflater.from(parent.context), parent, false))

    // 2选1，目前我选的是1。
    // 1、支持Binding.bind：好处是在子类bind方法内可直接使用控件，坏处是onCreateViewHolder在多样式时需要进行強转。Binding泛型不需要是out。
    // 2、不支持Binding.bind：好处是onCreateViewHolder在多样式时不需要进行強转，坏处是在子类bind方法内不可直接使用控件。Binding泛型需要是out。
    abstract fun Binding.bind(position: Int, item: Item)

    fun bindData(position: Int, item: Item) {
        binding.bind(position, item)
    }

}