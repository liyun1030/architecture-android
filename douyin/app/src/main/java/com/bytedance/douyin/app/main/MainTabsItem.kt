package com.bytedance.douyin.app.main

import com.bytedance.core.common.util.asUnsafe
import com.bytedance.douyin.core.model.MainTabType
import com.bytedance.core.model.BaseFragmentStateDiffItem

/**
 * 描述：
 *
 * @author zhangrq
 * createTime 2024/10/22 16:20
 */
data class MainTabsItem(
    // 标题资源Id
    val titleResId: Int,
    // Tab类型：首页、朋友、拍摄、消息、我的、商城
    val type: MainTabType,
    // 点击事件，更新MainTab为商城或好友。
    val updateMainTabShopOrFriend: (Boolean) -> Unit,
) : BaseFragmentStateDiffItem {

    // 商城和好友可互相换，所以此2个返回的值需要相同才会触发adapter的update，否则会依次触发adapter的remove、insert，导致ViewPager2会滚动到其他页。
    override fun getPrimaryKey() =
        if (type == MainTabType.SHOP) MainTabType.FRIEND else type

    override fun <T> areContentsTheSame(newItem: T): Boolean {
        val mainTabsItem = newItem.asUnsafe<MainTabsItem>()
        return titleResId == mainTabsItem.titleResId && type == mainTabsItem.type
    }

    override fun getItemId() = type.ordinal.toLong()
}