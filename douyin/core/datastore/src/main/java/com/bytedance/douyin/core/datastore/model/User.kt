package com.bytedance.douyin.core.datastore.model

import com.bytedance.douyin.core.model.User
import com.bytedance.douyin.core.datastore.User as LocalUser

/**
 * 描述：
 *
 * @author zhangrq
 * createTime 2024/11/29 10:05
 */
// 用户，本地类->外部类。
fun LocalUser.asExternalModel() = User(id, token, account, createdAt)

// 用户，外部类->本地类。
fun User.asLocalModel(): LocalUser = LocalUser.newBuilder()
    .setId(id)
    .setToken(token)
    .setAccount(account)
    .setCreatedAt(createdAt)
    .build()