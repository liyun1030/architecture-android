package com.bytedance.douyin.feature.video.ui.video

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.bytedance.douyin.core.architecture.app.views.AppViewsFragment
import com.bytedance.douyin.core.architecture.util.setDataOrAdapter
import com.bytedance.douyin.core.common.interfaces.OnFragmentBackgroundListener
import com.bytedance.douyin.core.common.interfaces.OnTabClickRefreshFinishListener
import com.bytedance.douyin.core.common.interfaces.OnTabClickRefreshListener
import com.bytedance.douyin.core.common.util.setStatusBarDarkFont
import com.bytedance.douyin.feature.video.ui.video.VideoUiState
import com.zrq.test.point.annotation.TestEntryPoint
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import com.bytedance.douyin.feature.video.databinding.DouyinFeatureVideoFragmentVideoBinding as ViewBinding
import com.bytedance.douyin.feature.video.ui.video.VideoUiState as UiState
import com.bytedance.douyin.feature.video.ui.video.VideoViewModel as ViewModel

/**
 * 描述：
 *
 * @author zhangrq
 * createTime 2024/4/1 下午3:33
 */
@TestEntryPoint("视频")
@AndroidEntryPoint
class VideoFragment : AppViewsFragment<ViewBinding, UiState, ViewModel>(),
    OnFragmentBackgroundListener,
    OnTabClickRefreshListener {
    override val viewModel: ViewModel by viewModels()

    override var isBackgroundBright: Boolean = false

    override fun inflateViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
    ) = ViewBinding.inflate(inflater, container, false)

    private val fragmentStateAdapter by lazy { VideoFragmentStateAdapter(this) }

    override fun ViewBinding.initViews() {
    }

    override fun ViewBinding.initListeners() {
    }

    override fun ViewBinding.initObservers() {
    }

    override fun ViewBinding.onUiStateCollect(uiState: VideoUiState) {
        viewPager2.setDataOrAdapter(uiState.list, null) { fragmentStateAdapter }
    }

    override fun onResume() {
        super.onResume()
        setStatusBarDarkFont(isDarkFont = isBackgroundBright)
    }

    override fun onTabClickRefresh(listener: OnTabClickRefreshFinishListener) {
        // 模拟网络数据加载成功
        lifecycleScope.launch {
            delay(2000)
            listener.onTabClickRefreshFinish()
        }
    }

    companion object {
        internal fun newInstance() = VideoFragment()
    }
}