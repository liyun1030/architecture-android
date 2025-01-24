package com.bytedance.core.model

/**
 * 描述：
 *
 * @author zhangrq
 * createTime 2024/10/18 15:30
 */
interface BaseFragmentStateDiffItem : BaseDiffItem {
    fun getItemId(): Long
}