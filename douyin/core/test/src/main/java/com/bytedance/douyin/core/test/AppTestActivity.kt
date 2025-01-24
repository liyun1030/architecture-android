package com.bytedance.douyin.core.test

import android.app.Activity
import com.bytedance.core.test.TestActivity

/**
 * 描述：
 *
 * @author zhangrq
 * createTime 2024/10/23 11:36
 */
class AppTestActivity : TestActivity() {
    companion object {

        fun startActivity(activity: Activity, contentResId: Int) =
            startActivity(activity, contentResId, AppTestActivity::class.java)

        fun startActivity(activity: Activity, content: String) =
            startActivity(activity, content, AppTestActivity::class.java)
    }

}