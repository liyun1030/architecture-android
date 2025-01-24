package com.bytedance.douyin.feature.home.ui.hometabssort

import com.bytedance.core.model.BaseDiffItem
import com.bytedance.douyin.core.model.HomeTabType

/**
 * 描述：
 *
 * @author zhangrq
 * createTime 2024/10/24 15:13
 */
data class HomeTabsSortItem(
    // 标题资源Id
    val titleResId: Int,
    // Tab类型：热点、直播、精选、同城、团购、关注、经验、商城（朋友）、推荐。
    val type: HomeTabType,
) : BaseDiffItem {
    override fun getPrimaryKey() = type
}