package com.bytedance.core.architecture.interfaces

import com.bytedance.core.architecture.stateview.LoadStateUiState
import kotlinx.coroutines.flow.MutableStateFlow

/**
 * 描述：
 *
 * @author zhangrq
 * createTime 2024/12/18 10:29
 */
interface LoadStateUiStateOwner {
    val loadStateUiState: MutableStateFlow<LoadStateUiState?>
}