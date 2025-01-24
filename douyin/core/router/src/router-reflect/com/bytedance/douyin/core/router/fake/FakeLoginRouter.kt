package com.bytedance.douyin.core.router.fake

import android.app.Activity
import com.bytedance.douyin.core.router.interfaces.LoginRouter
import com.bytedance.douyin.core.test.AppTestActivity

/**
 * 描述：
 *
 * @author zhangrq
 * createTime 2024/3/19 下午5:15
 */
class FakeLoginRouter : LoginRouter {

    override fun startLoginActivity(activity: Activity) {
        AppTestActivity.startActivity(activity, "LoginActivity")
    }
}