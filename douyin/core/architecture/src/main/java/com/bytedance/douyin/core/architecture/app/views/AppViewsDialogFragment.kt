package com.bytedance.douyin.core.architecture.app.views

import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import com.bytedance.core.architecture.base.BaseViewModel
import com.bytedance.core.architecture.base.views.BaseViewsDialogFragment
import com.bytedance.douyin.core.designsystem.util.createAppStateView

/**
 * 描述：
 *
 * @author zhangrq
 * createTime 2024/10/24 10:05
 */
abstract class AppViewsDialogFragment<Binding : ViewBinding, UiState : Any, ViewModel : BaseViewModel<UiState>> :
    BaseViewsDialogFragment<Binding, UiState, ViewModel>() {
    // 全部使用反射来实现
//    override val viewModel: ViewModel by reflectViewModels()

    override fun createStateView(stateViewParent: ViewGroup) = createAppStateView(stateViewParent)

    // 消息的收集者，默认为Toast，子类可覆写为Snakbar等。
//    override val messageCollector: MessageCollector by lazy { ToastMessageCollector(requireContext()) }
//    override val messageCollector: MessageCollector by lazy { SnakbarViewMessageCollector(requireView())}

    // 全部使用反射来实现
//    override fun inflateViewBinding(inflater: LayoutInflater, container: ViewGroup?) =
//        reflectInflateViewBinding(inflater, container)
}