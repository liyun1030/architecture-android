package com.bytedance.douyin.feature.login.ui.retrievepassword

import androidx.lifecycle.SavedStateHandle
import com.bytedance.core.architecture.stateview.isSuccess
import com.bytedance.douyin.core.data.repository.interfaces.LoginRepository
import com.bytedance.douyin.feature.login.ui.base.BaseLoginUiState
import com.bytedance.douyin.feature.login.ui.base.BaseLoginViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import javax.inject.Inject
import com.bytedance.douyin.feature.login.ui.retrievepassword.RetrievePasswordNewPasswordUiState as UiState

/**
 * 描述：
 *
 * @author zhangrq
 * createTime 2024/9/3 上午10:53
 */
@HiltViewModel
class RetrievePasswordNewPasswordViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val loginRepository: LoginRepository,
) : BaseLoginViewModel<UiState>(savedStateHandle) {

    // UiState初始化值
    override val uiStateInitialValue: UiState = UiState(password = password.value)

    // UiState的Flow
    override val uiStateFlow: Flow<UiState> = combine(
        password, loadStateUiState
    ) { password, loadStateUiState ->
        // 转UiState
        UiState(
            // base
            baseLoginUiState = createBaseLoginUiState(
                isPasswordValid(),
                loadStateUiState
            ),
            // other
            password = password,
            isRetrievePasswordSucceed = loadStateUiState.isSuccess()
        )
    }

    fun complete() {
        if (checkPasswordCodeValid()) {
            // 检查成功，请求异步获取数据。
            requestAsync {
                // 登录
                loginRepository.retrievePasswordNewPassword(
                    argsPhoneNumber.toString(),
                    password.value.toString()
                )
            }
        }
    }
}

data class RetrievePasswordNewPasswordUiState(
    val baseLoginUiState: BaseLoginUiState = BaseLoginUiState(),
    val password: String? = null,
    val isRetrievePasswordSucceed: Boolean = false,
)