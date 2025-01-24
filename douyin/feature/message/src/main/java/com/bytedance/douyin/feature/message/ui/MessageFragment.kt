package com.bytedance.douyin.feature.message.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import com.bytedance.douyin.core.architecture.app.views.AppViewsEmptyViewModelFragment
import com.bytedance.douyin.core.common.interfaces.OnFragmentBackgroundListener
import com.bytedance.douyin.core.common.interfaces.OnTabClickAgainListener
import com.bytedance.douyin.core.common.util.setStatusBarDarkFont
import com.zrq.test.point.annotation.TestEntryPoint
import dagger.hilt.android.AndroidEntryPoint
import com.bytedance.douyin.feature.message.databinding.DouyinFeatureMessageFragmentMessageBinding as ViewBinding

/**
 * 描述：
 *
 * @author zhangrq
 * createTime 2024/3/11 下午3:20
 */
@TestEntryPoint("消息")
@AndroidEntryPoint
class MessageFragment : AppViewsEmptyViewModelFragment<ViewBinding>(), OnTabClickAgainListener,
    OnFragmentBackgroundListener {
    override var isBackgroundBright: Boolean = true

    override fun inflateViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
    ) = ViewBinding.inflate(inflater, container, false)

    override fun ViewBinding.initViews() {

    }

    override fun ViewBinding.initListeners() {
    }

    override fun ViewBinding.initObservers() {
    }

    override fun onResume() {
        super.onResume()
        setStatusBarDarkFont(isDarkFont = isBackgroundBright)
    }

    override fun onTabClickAgain() {
    }

    companion object {
        internal fun newInstance() = MessageFragment()
    }
}