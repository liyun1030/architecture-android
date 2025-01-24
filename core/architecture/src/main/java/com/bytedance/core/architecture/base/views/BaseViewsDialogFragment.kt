package com.bytedance.core.architecture.base.views

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.FrameLayout
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.viewbinding.ViewBinding
import com.bytedance.core.architecture.base.BaseViewModel
import com.bytedance.core.architecture.interfaces.DialogInit
import com.bytedance.core.architecture.interfaces.ViewBindingFragment
import com.bytedance.core.architecture.interfaces.ViewModelOwner
import com.bytedance.core.architecture.message.MessageCollector
import com.bytedance.core.architecture.message.MessageCollectorOwner
import com.bytedance.core.architecture.message.collector.ToastMessageCollector
import com.bytedance.core.architecture.stateview.interfaces.DefaultStateViewManager
import com.bytedance.core.architecture.stateview.interfaces.StateViewManager
import com.bytedance.core.architecture.stateview.util.initLoadStateUiStateCollect
import com.bytedance.core.architecture.stateview.util.removeAllNoStateViewChildren
import com.bytedance.core.common.util.asSafe
import com.bytedance.core.designsystem.widget.interfaces.LoadingDialog
import com.bytedance.core.designsystem.widget.interfaces.StateView
import kotlinx.coroutines.launch

/**
 * 描述：
 *
 * @author zhangrq
 * createTime 2024/10/24 10:04
 */
abstract class BaseViewsDialogFragment<Binding : ViewBinding, UiState : Any, ViewModel : BaseViewModel<UiState>> :
    DialogFragment(), ViewBindingFragment<Binding, UiState>, ViewModelOwner<UiState, ViewModel>,
    DefaultStateViewManager, MessageCollectorOwner, DialogInit {
    private var _binding: Binding? = null
    private val rootViewGroup by lazy { FrameLayout(requireContext()) }

    // 此属性仅在onCreateView、onDestroyView之间有效。
    override val binding get() = _binding

    // 此处为了性能，没用反射直接获取，而是让调用者自己选择。可在AppViewsFragment覆盖此实现（使用反射），或者每个具体子类（如HomeFragment）具体类型实现。
//    override val viewModel: ViewModel by reflectViewModels()

    // StateView相关
    // -stateViewParent只有你调用的时候，才会在第一次添加。
    override val stateView: Lazy<StateView> = lazy { createStateViewImpl() }

    // -LoadingDialog，先用Activity的，如果没有则只有你调用的时候，才会在第一次创建。
    override val loadingDialog: Lazy<LoadingDialog> = lazy {
        (activity.asSafe<StateViewManager>()?.loadingDialog?.value
            ?: createLoadingDialogImpl(requireActivity()))
    }

    // -StateView要替换的View，默认为根布局。
    // -说明：因为所有操作StateView的都是在initStateUiStateCollect内操作，即在Lifecycle.State.STARTED时执行的，所以_binding一定不为空。
    override fun getStateViewReplaceView(): View = _binding!!.root

    // 消息的收集者，默认为Toast，子类可覆写为Snakbar等。
    override val messageCollector: MessageCollector by lazy { ToastMessageCollector(requireContext()) }
//    override val messageCollector: MessageCollector by lazy { SnakbarViewMessageCollector(requireView())}

    // 此处为了性能，没用反射直接获取，而是让调用者自己选择。可在AppViewsActivity覆盖此实现（使用反射），或者每个具体子类（如HomeFragment）具体类型实现。
//    override fun inflateViewBinding(inflater: LayoutInflater, container: ViewGroup?) =
//        reflectInflateViewBinding(inflater, container)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // 此处为了性能，没用反射直接获取，而是让子类直接实现。自己可在反射和子类实现两者之间进行选择。
        _binding = inflateViewBinding(inflater, container)
        // 为了实现StateView效果，需要多包一层，root.parent是不确定的（有可能是LinearLayout），所以得需要包一层解决。
        // -重置View，移除掉所有非StateView的view，防止Fragment-View销毁，但是Fragment没有销毁，而导致rootViewGroup没还原，导致下面内容被添加多次。
        rootViewGroup.removeAllNoStateViewChildren()
        // -添加
        rootViewGroup.addView(_binding!!.root, 0) // 必须在最下层
        return rootViewGroup
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // 初始化-OnViewCreated
        initOnViewCreated()
        // 初始化-WindowInsets
        // 说明：在此初始化WindowInsets而没在initListeners内初始化，是因为lazy的Fragment只有在onResume显示时才调用initListeners，导致更新布局位置慢了。
        binding!!.initWindowInsets()
        // 初始化-所有
        with(binding!!) {
            // 初始化
            initViews()
            initListeners()
            initBaseObservers()
            initObservers()
        }
    }

    override fun Binding.initBaseObservers() {
        // 初始化UiState收集
        // -必须用viewLifecycleOwner.lifecycleScope.launch{}，否则里面的收集被调用多次，而没取消，导致同一个数据会被多个接收者接收。
        // --说明：因为页面跳转，View销毁，但是Fragment没有，所以不能用范围广的lifecycleScope，而是用view的lifecycleScope。(lifecycle同理)
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.uiState.flowWithLifecycle(viewLifecycleOwner.lifecycle).collect { uiState ->
                onUiStateCollect(uiState)
            }
        }
        // 初始化Message收集
        // -说明：使用Activity的lifecycleScope、lifecycle会收集多次，所以不能在此使用，也不能覆盖此实现。
        messageCollector.initCollect(
            viewModel.messageController,
            viewLifecycleOwner.lifecycleScope,
            viewLifecycleOwner.lifecycle,
        )
        // 初始化LoadStateUiState收集
        initLoadStateUiStateCollect(
            viewModel,
            viewLifecycleOwner.lifecycleScope,
            viewLifecycleOwner.lifecycle
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    // ==================上面是Fragment的设置，下面是Dialog的设置==================

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return super.onCreateDialog(savedInstanceState).apply {
            initDialog(this)
        }
    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.apply {
            // 去掉默认背景
            initDialogWindow(this)
        }
    }

    override fun initDialog(dialog: Dialog) {
        // 设置点击外面可取消
        dialog.setCanceledOnTouchOutside(true)
        // 说明：
        // 1、不能直接设置dialog.setOnCancelListener、dialog.setOnDismissListener，这两个已经设置了，请直接使用此DialogFragment的onCancel、onDismiss来监听。
        // 2、不能直接设置dialog.setCancelable，这一个已经设置了，请直接使用此DialogFragment的setCancelable来设置。
    }

    override fun initDialogWindow(window: Window) {
        // 设置背景为空，解决默认背景有间距问题。
        window.setBackgroundDrawable(null)
    }
}