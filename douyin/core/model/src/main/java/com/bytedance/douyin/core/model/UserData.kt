package com.bytedance.douyin.core.model

/**
 * 描述：
 *
 * @author zhangrq
 * createTime 2024/3/13 上午10:57
 */
data class UserData(
    // 用户Id
    val user: User,
    // 主页Tabs。
    val mainTabs: List<Int>,
    // 首页Tabs。
    val homeTabs: List<Int>,
    // 是否使用动态颜色
    val useDynamicColor: Boolean,
)