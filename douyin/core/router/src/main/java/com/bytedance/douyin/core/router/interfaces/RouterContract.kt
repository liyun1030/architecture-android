package com.bytedance.douyin.core.router.interfaces


/**
 * 描述：
 *
 * @author zhangrq
 * createTime 2024/12/4 15:20
 */
@Suppress("PropertyName")
interface RouterContract {
    val Home: HomeRouter
    val Message: MessageRouter
    val Mine: MineRouter
    val Video: VideoRouter
    val Login: LoginRouter
}