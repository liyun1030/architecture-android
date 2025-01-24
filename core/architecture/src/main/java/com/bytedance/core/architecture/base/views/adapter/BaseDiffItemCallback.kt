package com.bytedance.core.architecture.base.views.adapter

import androidx.recyclerview.widget.DiffUtil
import com.bytedance.core.model.BaseDiffItem

/**
 * 描述：
 *
 * @author zhangrq
 * createTime 2024/3/5 上午11:33
 */
open class BaseDiffItemCallback<T : BaseDiffItem> : DiffUtil.ItemCallback<T>() {
    override fun areItemsTheSame(oldItem: T, newItem: T): Boolean {
        // 一级判断（简单），通常使用id字段判断。
        return oldItem.getPrimaryKey() == newItem.getPrimaryKey()
    }

    override fun areContentsTheSame(oldItem: T, newItem: T): Boolean {
        // 二级判断（复杂，areItemsTheSame为true下调用），通常使用item内的数据内容判断。
        return oldItem.areContentsTheSame(newItem)
    }

    override fun getChangePayload(oldItem: T, newItem: T): Any? {
        // 可选实现（areItemsTheSame为true、并且areContentsTheSame为false下调用），局部更新时用，获取Item布局局部更新的数据对象。
        return oldItem.getChangePayload(newItem)
    }
}