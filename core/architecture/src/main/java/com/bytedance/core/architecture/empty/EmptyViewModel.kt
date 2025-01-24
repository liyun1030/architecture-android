package com.bytedance.core.architecture.empty

import com.bytedance.core.architecture.base.BaseViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

/**
 * 描述：
 *
 * @author zhangrq
 * createTime 2024/8/12 上午10:22
 */
class EmptyViewModel : BaseViewModel<EmptyUiState>() {
    override val uiStateInitialValue: EmptyUiState = EmptyUiState()
    override val uiStateFlow: Flow<EmptyUiState> = emptyFlow()
}

class EmptyUiState