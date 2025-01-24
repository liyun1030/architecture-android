package com.bytedance.douyin.core.data.model

import com.bytedance.douyin.core.model.User
import com.bytedance.douyin.core.network.model.NetworkUser

/**
 * 描述：
 *
 * @author zhangrq
 * createTime 2024/11/29 09:27
 */
// 用户，网络类->外部类。
fun NetworkUser.asExternalModel() = User(id, token, account, createdAt)