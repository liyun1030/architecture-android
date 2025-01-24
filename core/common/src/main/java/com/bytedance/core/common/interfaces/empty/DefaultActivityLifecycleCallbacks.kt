package com.bytedance.core.common.interfaces.empty

import android.app.Activity
import android.app.Application.ActivityLifecycleCallbacks
import android.os.Bundle

/**
 * 描述：
 *
 * @author zhangrq
 * createTime 2024/8/12 上午9:50
 */
open class DefaultActivityLifecycleCallbacks : ActivityLifecycleCallbacks {
    override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
    }

    override fun onActivityStarted(activity: Activity) {
    }

    override fun onActivityResumed(activity: Activity) {
    }

    override fun onActivityPaused(activity: Activity) {
    }

    override fun onActivityStopped(activity: Activity) {
    }

    override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {
    }

    override fun onActivityDestroyed(activity: Activity) {
    }
}