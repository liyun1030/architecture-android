package com.bytedance.douyin.feature.mine.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.bytedance.core.common.util.asSafe
import com.bytedance.douyin.core.architecture.app.views.AppViewsFragment
import com.bytedance.douyin.core.common.interfaces.OnFragmentBackgroundListener
import com.bytedance.douyin.core.common.interfaces.OnOpenDrawerListener
import com.bytedance.douyin.core.common.util.setStatusBarDarkFont
import com.bytedance.douyin.core.login.login.LoginManager
import com.zrq.test.point.annotation.TestEntryPoint
import dagger.hilt.android.AndroidEntryPoint
import com.bytedance.douyin.feature.mine.databinding.DouyinFeatureMineFragmentMineBinding as ViewBinding
import com.bytedance.douyin.feature.mine.ui.MineUiState as UiState
import com.bytedance.douyin.feature.mine.ui.MineViewModel as ViewModel

/**
 * 描述：
 *
 * @author zhangrq
 * createTime 2024/3/11 下午3:21
 */
@TestEntryPoint("我的")
@AndroidEntryPoint
class MineFragment : AppViewsFragment<ViewBinding, UiState, ViewModel>(),
    OnFragmentBackgroundListener {
    override val viewModel: ViewModel by viewModels()
    override var isBackgroundBright: Boolean = true

    private var isStatusBarDarkFont = false

    override fun inflateViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
    ) = ViewBinding.inflate(inflater, container, false)

    override fun ViewBinding.initViews() {
    }

    override fun ViewBinding.initListeners() {
        // 点击
        openEndDrawer.setOnClickListener {
            activity.asSafe<OnOpenDrawerListener>()?.onOpenEndDrawer()
        }
        logout.setOnClickListener {
            LoginManager.logout()
        }
        // 动态颜色
        dynamicColorsSwitch.setOnCheckedChangeListener { _, isChecked ->
            viewModel.updateDynamicColorPreference(isChecked) {
                // 修改完成，重新创建Activity。
                activity?.recreate()
            }
        }
        // 滚动
        nestedScrollView.setOnScrollChangeListener { _, _, scrollY, _, _ ->
            isStatusBarDarkFont = scrollY > topBackground.height
            setStatusBarDarkFont(isStatusBarDarkFont)
        }
    }

    override fun ViewBinding.initObservers() {
    }

    override fun ViewBinding.onUiStateCollect(uiState: UiState) {
        dynamicColorsSwitch.isChecked = uiState.useDynamicColor
    }

    override fun onResume() {
        super.onResume()
        setStatusBarDarkFont(isDarkFont = isStatusBarDarkFont)
    }

    companion object {
        internal fun newInstance() = MineFragment()
    }
}