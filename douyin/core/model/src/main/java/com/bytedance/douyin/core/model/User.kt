package com.bytedance.douyin.core.model

/**
 * 描述：
 *
 * @author zhangrq
 * createTime 2024/8/29 下午4:15
 */
data class User(
    val id: Int,
    val token: String,
    val account: String,
    val createdAt: String,
)
