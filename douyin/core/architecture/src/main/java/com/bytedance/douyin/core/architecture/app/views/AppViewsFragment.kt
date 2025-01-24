package com.bytedance.douyin.core.architecture.app.views

import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import com.bytedance.core.architecture.base.BaseViewModel
import com.bytedance.core.architecture.base.views.BaseViewsFragment
import com.bytedance.core.common.util.getDataFromThemeAttr
import com.bytedance.core.designsystem.widget.interfaces.LoadingDialog
import com.bytedance.douyin.core.common.interfaces.OnFragmentBackgroundListener
import com.bytedance.douyin.core.designsystem.util.createAppStateView
import com.google.android.material.R

/**
 * 描述：
 *
 * @author zhangrq
 * createTime 2024/8/12 下午4:54
 */
abstract class AppViewsFragment<Binding : ViewBinding, UiState : Any, ViewModel : BaseViewModel<UiState>> :
    BaseViewsFragment<Binding, UiState, ViewModel>() {
    // 全部使用反射来实现
//    override val viewModel: ViewModel by reflectViewModels()

    override fun createStateView(stateViewParent: ViewGroup) = createAppStateView(stateViewParent)

    // 消息的收集者，默认为Toast，子类可覆写为Snakbar等。
//    override val messageCollector: MessageCollector by lazy { ToastMessageCollector(requireContext()) }
//    override val messageCollector: MessageCollector by lazy { SnakbarViewMessageCollector(requireView())}

    // 全部使用反射来实现
//    override fun inflateViewBinding(inflater: LayoutInflater, container: ViewGroup?) =
//        reflectInflateViewBinding(inflater, container)

    override fun initLoadingDialog(loadingDialog: LoadingDialog) {
    }

    override fun initOnViewCreated() {
        super.initOnViewCreated()
        if (this is OnFragmentBackgroundListener) {
            // 设置通用背景
            binding?.root?.setBackgroundColor(requireContext().getDataFromThemeAttr(if (isBackgroundBright) R.attr.colorSurface else R.attr.colorSurfaceInverse))
        }
    }

}