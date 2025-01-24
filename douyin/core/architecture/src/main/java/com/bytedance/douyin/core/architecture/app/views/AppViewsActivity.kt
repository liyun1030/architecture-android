package com.bytedance.douyin.core.architecture.app.views

import android.os.Build
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import com.bytedance.core.architecture.base.BaseViewModel
import com.bytedance.core.architecture.base.views.BaseViewsActivity
import com.bytedance.core.designsystem.widget.interfaces.LoadingDialog
import com.bytedance.douyin.core.designsystem.util.createAppStateView

/**
 * 描述：
 *
 * @author zhangrq
 * createTime 2024/2/27 上午9:56
 */
abstract class AppViewsActivity<Binding : ViewBinding, UiState : Any, ViewModel : BaseViewModel<UiState>> :
    BaseViewsActivity<Binding, UiState, ViewModel>() {
    // 全部使用反射来实现
//    override val viewModel: ViewModel by reflectViewModels()

    override fun createStateView(stateViewParent: ViewGroup) = createAppStateView(stateViewParent)

    // 消息的收集者，默认为Toast，可覆写为Snakbar等。
//    override val messageCollector: MessageCollector by lazy { ToastMessageCollector(applicationContext) }
//    override val messageCollector: MessageCollector by lazy { SnakbarViewMessageCollector(binding.root) }

    override fun initEdgeToEdge() {
        super.initEdgeToEdge()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            // 导航栏透明
            window.isNavigationBarContrastEnforced = false
        }
    }

    // 全部使用反射来实现
//    override fun inflateViewBinding(inflater: LayoutInflater) = reflectInflateViewBinding(inflater)

    override fun initLoadingDialog(loadingDialog: LoadingDialog) {
        // 不能去取消
        loadingDialog.dialog.setCancelable(false)
        loadingDialog.dialog.setCanceledOnTouchOutside(false)
    }
}