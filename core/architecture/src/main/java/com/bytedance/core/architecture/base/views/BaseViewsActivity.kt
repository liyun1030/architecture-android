package com.bytedance.core.architecture.base.views

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.viewbinding.ViewBinding
import com.bytedance.core.architecture.base.BaseActivity
import com.bytedance.core.architecture.base.BaseViewModel
import com.bytedance.core.architecture.interfaces.ViewBindingActivity
import com.bytedance.core.architecture.interfaces.ViewModelOwner
import com.bytedance.core.architecture.message.MessageCollector
import com.bytedance.core.architecture.message.MessageCollectorOwner
import com.bytedance.core.architecture.message.collector.ToastMessageCollector
import com.bytedance.core.architecture.stateview.interfaces.DefaultStateViewManager
import com.bytedance.core.architecture.stateview.util.initLoadStateUiStateCollect
import com.bytedance.core.designsystem.widget.interfaces.LoadingDialog
import com.bytedance.core.designsystem.widget.interfaces.StateView
import kotlinx.coroutines.launch


/**
 * 描述：
 *
 * @author zhangrq
 * createTime 2024/2/26 上午9:09
 */
abstract class BaseViewsActivity<Binding : ViewBinding, UiState : Any, ViewModel : BaseViewModel<UiState>> :
    BaseActivity(), ViewBindingActivity<Binding, UiState>, ViewModelOwner<UiState, ViewModel>,
    DefaultStateViewManager, MessageCollectorOwner {

    override val binding: Binding by lazy { inflateViewBinding(layoutInflater) }

    // 此处为了性能，没用反射直接获取，而是让调用者自己选择。可在AppViewsActivity覆盖此实现（使用反射），或者每个具体子类（如MainActivity）具体类型实现。
//    override val viewModel: ViewModel by reflectViewModels()

    // StateView相关
    // -stateView只有你调用的时候，才会在第一次添加。
    override val stateView: Lazy<StateView> = lazy { createStateViewImpl() }

    // -LoadingDialog只有你调用的时候，才会在第一次创建。
    override val loadingDialog: Lazy<LoadingDialog> = lazy { createLoadingDialogImpl(this) }

    // -StateView要替换的View，默认为根布局。
    override fun getStateViewReplaceView(): View = binding.root

    // 消息的收集者，默认为Toast，子类可覆写为Snakbar等。
    override val messageCollector: MessageCollector by lazy {
        ToastMessageCollector(
            applicationContext
        )
    }
//    override val messageCollector: MessageCollector by lazy { SnakbarViewMessageCollector(binding.root) }

    // 此处为了性能，没用反射直接获取，而是让调用者自己选择。可在AppViewsActivity覆盖此实现（使用反射），或者每个具体子类（如MainActivity）具体类型实现。
//    override fun inflateViewBinding(inflater: LayoutInflater) = reflectInflateViewBinding(inflater)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initEdgeToEdge()
        // 内容
        setContentView(binding.root)
        // 初始化
        binding.initWindowInsets()
        binding.initViews()
        binding.initListeners()
        binding.initBaseObservers()
        binding.initObservers()
    }

    open fun initEdgeToEdge() {
        enableEdgeToEdge()
    }

    override fun Binding.initBaseObservers() {
        // 初始化UiState收集
        lifecycleScope.launch {
            viewModel.uiState
                .flowWithLifecycle(lifecycle)
                .collect { uiState ->
                    onUiStateCollect(uiState)
                }
        }
        // 初始化Message收集
        messageCollector.initCollect(
            viewModel.messageController,
            lifecycleScope,
            lifecycle,
        )

        // 初始化LoadStateUiState收集
        initLoadStateUiStateCollect(viewModel, lifecycleScope, lifecycle)
    }

}