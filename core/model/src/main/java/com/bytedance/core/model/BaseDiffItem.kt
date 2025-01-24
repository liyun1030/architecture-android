package com.bytedance.core.model

/**
 * 描述：
 *
 * @author zhangrq
 * createTime 2024/3/5 下午3:46
 */
interface BaseDiffItem {
    /**
     * 用于一级的（areItemsTheSame）比较，获取主key。
     */
    fun getPrimaryKey(): Any

    /**
     * 用于二级的（areContentsTheSame）比较，默认比较整个对象自己，可修改为比较部分属性。
     */
    fun <T> areContentsTheSame(newItem: T): Boolean = this == newItem

    /**
     * 用于可选实现（getChangePayload），局部更新时用，获取Item布局局部更新的数据对象。
     * 说明：
     * 1、返回null，Item布局更新有动画，否则无动画。
     * 2、需要配合RecyclerView的onBindViewHolder(holder,position,payloads)使用，在payloads内获取传入的值。
     *
     */
    fun <T> getChangePayload(newItem: T): Any? = newItem
}