package com.bytedance.douyin.core.model

import com.bytedance.core.model.BaseFragmentStateDiffItem

/**
 * 描述：
 *
 * @author zhangrq
 * createTime 2024/3/1 下午2:23
 */
data class VideoItem(val id: Long) : BaseFragmentStateDiffItem {
    override fun getPrimaryKey() = id

    override fun getItemId() = id
}