package com.bytedance.core.architecture.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bytedance.core.architecture.interfaces.LoadStateUiStateOwner
import com.bytedance.core.architecture.interfaces.UiStateOwner
import com.bytedance.core.architecture.message.MessageController
import com.bytedance.core.architecture.message.MessageDisplayManager
import com.bytedance.core.architecture.message.controller.DefaultMessageController
import com.bytedance.core.architecture.stateview.LoadStateUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn

/**
 * 描述：
 *
 * @author zhangrq
 * createTime 2024/2/26 下午5:50
 */
abstract class BaseViewModel<UiState> : ViewModel(), UiStateOwner<UiState>, LoadStateUiStateOwner,
    MessageDisplayManager {
    // 消息控制者，子类可覆盖其实现。
    override val messageController: MessageController = DefaultMessageController()

    // 加载状态-UiState
    override val loadStateUiState = MutableStateFlow<LoadStateUiState?>(null)

    // UiState配置，子类可覆盖其实现。
    override val uiState: StateFlow<UiState> by lazy {
        uiStateFlow.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = uiStateInitialValue,
        )
    }

}