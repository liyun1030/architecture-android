package com.bytedance.douyin.core.architecture.app.views

import androidx.activity.viewModels
import androidx.viewbinding.ViewBinding
import com.bytedance.core.architecture.empty.EmptyUiState
import com.bytedance.core.architecture.empty.EmptyViewModel

/**
 * 描述：
 *
 * @author zhangrq
 * createTime 2024/2/26 下午2:45
 */
abstract class AppViewsEmptyViewModelActivity<Binding : ViewBinding> :
    AppViewsActivity<Binding, EmptyUiState, EmptyViewModel>() {
    override val viewModel: EmptyViewModel by viewModels()

    override fun Binding.onUiStateCollect(uiState: EmptyUiState) {
    }
}