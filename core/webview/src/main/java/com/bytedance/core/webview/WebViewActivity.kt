package com.bytedance.core.webview

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.activity.viewModels
import com.bytedance.core.architecture.base.views.BaseViewsActivity
import com.bytedance.core.architecture.empty.EmptyUiState
import com.bytedance.core.architecture.empty.EmptyViewModel
import com.bytedance.core.designsystem.widget.interfaces.StateView
import com.bytedance.core.webview.databinding.CoreWebviewActivityWebViewBinding as ViewBinding

// TODO 后续统一优化整理WebView相关
class WebViewActivity : BaseViewsActivity<ViewBinding, EmptyUiState, EmptyViewModel>() {
    override val viewModel: EmptyViewModel by viewModels()

    override fun createStateView(stateViewParent: ViewGroup): StateView {
        TODO("Not yet implemented")
    }

    override fun inflateViewBinding(inflater: LayoutInflater) =
        ViewBinding.inflate(inflater)

    override fun ViewBinding.initViews() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.webview_container_view, WebViewFragment.newFragment(intent.extras))
            .commit()
    }

    override fun ViewBinding.initListeners() {
    }

    override fun ViewBinding.initObservers() {
    }

    override fun ViewBinding.onUiStateCollect(uiState: EmptyUiState) {
    }

    companion object {
        fun startActivity(activity: Activity, url: String) {
            val intent = Intent(activity.applicationContext, WebViewActivity::class.java)
            intent.putExtras(WebViewFragment.newBundle(url))
            activity.startActivity(intent)
        }
    }
}