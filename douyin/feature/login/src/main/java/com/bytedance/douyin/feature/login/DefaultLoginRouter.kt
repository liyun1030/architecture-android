package com.bytedance.douyin.feature.login

import android.app.Activity
import com.bytedance.douyin.core.router.interfaces.LoginRouter
import com.bytedance.douyin.feature.login.ui.LoginActivity

/**
 * 描述：
 *
 * @author zhangrq
 * createTime 2024/3/19 下午5:15
 */
class DefaultLoginRouter : LoginRouter {
    override fun startLoginActivity(activity: Activity) {
        LoginActivity.startActivity(activity)
    }
}