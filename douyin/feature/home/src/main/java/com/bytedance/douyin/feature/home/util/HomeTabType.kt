package com.bytedance.douyin.feature.home.util

import com.bytedance.douyin.core.model.HomeTabType
import com.bytedance.douyin.feature.home.R

/**
 * 描述：
 *
 * @author zhangrq
 * createTime 2024/10/28 10:49
 */
internal fun HomeTabType.toHomeTabTitle() = when (this) {
    HomeTabType.HOT -> R.string.douyin_feature_home_tabs_hot
    HomeTabType.LIVE -> R.string.douyin_feature_home_tabs_live
    HomeTabType.CHOICE -> R.string.douyin_feature_home_tabs_choice
    HomeTabType.SAME_CITY -> R.string.douyin_feature_home_tabs_same_city
    HomeTabType.GROUP_BUYING -> R.string.douyin_feature_home_tabs_group_buying
    HomeTabType.FOLLOW -> R.string.douyin_feature_home_tabs_following
    HomeTabType.EXPERIENCE -> R.string.douyin_feature_home_tabs_experience
    HomeTabType.FRIEND -> R.string.douyin_feature_home_tabs_friend
    HomeTabType.RECOMMENDED -> R.string.douyin_feature_home_tabs_recommended
    HomeTabType.SHOP -> R.string.douyin_feature_home_tabs_shop
}