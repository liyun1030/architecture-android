package com.bytedance.core.architecture.interfaces

import com.bytedance.core.architecture.base.BaseViewModel

/**
 * 描述：
 *
 * @author zhangrq
 * createTime 2024/8/15 上午9:37
 */
interface ViewModelOwner<UiState : Any, ViewModel : BaseViewModel<UiState>> {
    val viewModel: ViewModel
}