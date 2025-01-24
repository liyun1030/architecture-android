package com.bytedance.douyin.feature.login.ui

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bytedance.douyin.core.architecture.app.views.AppViewsEmptyViewModelActivity
import com.bytedance.douyin.core.login.login.LoginManager
import com.zrq.test.point.annotation.TestEntryPoint
import dagger.hilt.android.AndroidEntryPoint
import com.bytedance.douyin.core.designsystem.R as designSystemR
import com.bytedance.douyin.feature.login.databinding.DouyinFeatureLoginActivityLoginBinding as ViewBinding

@TestEntryPoint("登录")
@AndroidEntryPoint
@Suppress("DEPRECATION")
class LoginActivity : AppViewsEmptyViewModelActivity<ViewBinding>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        overridePendingTransition(designSystemR.anim.douyin_core_designsystem_slide_in_bottom, 0)
        super.onCreate(savedInstanceState)
    }

    override fun inflateViewBinding(inflater: LayoutInflater) = ViewBinding.inflate(inflater)

    override fun ViewBinding.initWindowInsets() {
        // 由于登录页面的子Fragment没有背景，所以在此统一设置内间距即可实现，子Fragment不需要实现。
        ViewCompat.setOnApplyWindowInsetsListener(root) { v, insets ->
            val systemBars = insets.getInsets(
                WindowInsetsCompat.Type.systemBars() or WindowInsetsCompat.Type.displayCutout() or WindowInsetsCompat.Type.ime()
            )
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    override fun ViewBinding.initViews() {
    }

    override fun ViewBinding.initListeners() {
    }

    override fun ViewBinding.initObservers() {
    }

    override fun finish() {
        super.finish()
        overridePendingTransition(0, designSystemR.anim.douyin_core_designsystem_slide_out_bottom)
        // 清除自动登录的监听
        // 说明：不要在onDestroy()内调用此方法，而是在finish()内调用。因为配置变更会调用onDestroy()，会导致意外清除，而在finish()则只会在登录成功、用户手动返回才会触发，所以需要在finish()方法内调用。
        LoginManager.clearAutoLoginJob()
    }

    companion object {
        internal fun startActivity(activity: Activity) {
            activity.startActivity(Intent(activity.applicationContext, LoginActivity::class.java))
        }
    }
}