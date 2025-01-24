package com.bytedance.core.webview

import android.annotation.SuppressLint
import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.viewModels
import androidx.webkit.WebSettingsCompat
import androidx.webkit.WebViewFeature
import com.bytedance.core.architecture.base.views.BaseViewsFragment
import com.bytedance.core.architecture.empty.EmptyUiState
import com.bytedance.core.architecture.empty.EmptyViewModel
import com.bytedance.core.designsystem.widget.interfaces.StateView
import com.bytedance.core.webview.databinding.CoreWebviewFragmentWebViewBinding as ViewBinding

/**
 * 描述：
 *
 * @author zhangrq
 * createTime 2024/4/30 上午9:35
 */
// TODO 后续统一优化整理WebView相关
class WebViewFragment : BaseViewsFragment<ViewBinding, EmptyUiState, EmptyViewModel>() {
    override val viewModel: EmptyViewModel by viewModels()

    override fun createStateView(stateViewParent: ViewGroup): StateView {
        TODO("Not yet implemented")
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // 支持暗模式
        val nightModeFlag = resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK
        // Check if the system is set to light or dark mode
        if (nightModeFlag == Configuration.UI_MODE_NIGHT_YES) {
            // 设置深色主题
            if (WebViewFeature.isFeatureSupported(WebViewFeature.ALGORITHMIC_DARKENING)) {
                WebSettingsCompat.setAlgorithmicDarkeningAllowed(binding!!.webView.settings, true)
            }
        }
    }

    override fun inflateViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
    ) = ViewBinding.inflate(inflater, container, false)

    @SuppressLint("SetJavaScriptEnabled")
    override fun ViewBinding.initViews() {
        webView.settings.javaScriptEnabled = true
        val onBackPressedCallback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                when {
                    webView.canGoBack() -> webView.goBack()
                }
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(onBackPressedCallback)

        webView.webViewClient = object : WebViewClient() {
            // Prevents opening Chromium from the Android app
//            override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
//                return false
//            }

            //            override fun shouldOverrideUrlLoading(
//                view: WebView?,
//                request: WebResourceRequest?
//            ): Boolean {
//                return super.shouldOverrideUrlLoading(view, request)
//            }
            override fun doUpdateVisitedHistory(view: WebView?, url: String?, isReload: Boolean) {
                super.doUpdateVisitedHistory(view, url, isReload)
                onBackPressedCallback.isEnabled = webView.canGoBack()
            }
        }

        webView.loadUrl(arguments?.getString(ARGS_URL) ?: "")
    }

    override fun ViewBinding.initListeners() {
    }

    override fun ViewBinding.initObservers() {
    }

    override fun ViewBinding.onUiStateCollect(uiState: EmptyUiState) {
    }

    companion object {
        private const val ARGS_URL = "args_url"

        fun newFragment(args: Bundle?) = WebViewFragment().apply {
            arguments = args
        }

        fun newBundle(url: String) = Bundle().apply {
            putString(ARGS_URL, url)
        }
    }
}