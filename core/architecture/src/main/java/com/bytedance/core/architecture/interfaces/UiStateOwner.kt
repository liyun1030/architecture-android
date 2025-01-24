package com.bytedance.core.architecture.interfaces

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

/**
 * 描述：
 *
 * @author zhangrq
 * createTime 2024/8/13 上午10:11
 */
interface UiStateOwner<UiState> {

    val uiStateInitialValue: UiState
    val uiStateFlow: Flow<UiState>

    val uiState: StateFlow<UiState>
}