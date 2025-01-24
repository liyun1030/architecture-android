package com.bytedance.douyin.feature.login.ui.login.verificationcode

import androidx.lifecycle.SavedStateHandle
import com.bytedance.core.architecture.stateview.isSuccess
import com.bytedance.douyin.core.data.repository.interfaces.LoginRepository
import com.bytedance.douyin.feature.login.ui.base.BaseLoginUiState
import com.bytedance.douyin.feature.login.ui.base.BaseLoginViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import javax.inject.Inject
import com.bytedance.douyin.feature.login.ui.login.verificationcode.LoginByPhoneNumberAndVerificationCodeNextUiState as UiState

/**
 * 描述：
 *
 * @author zhangrq
 * createTime 2024/8/1 上午11:05
 */
@HiltViewModel
class LoginByPhoneNumberAndVerificationCodeNextViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val loginRepository: LoginRepository,
) : BaseLoginViewModel<UiState>(savedStateHandle) {

    // UiState初始化值
    override val uiStateInitialValue: UiState =
        UiState(phoneNumber = argsPhoneNumber, verificationCode = verificationCode.value)

    // UiState的Flow
    override val uiStateFlow: Flow<UiState> =
        combine(verificationCode, loadStateUiState) { verificationCode, loadStateUiState ->
            // 转UiState
            UiState(
                // base
                baseLoginUiState = createBaseLoginUiState(
                    isVerificationCodeValid(),
                    loadStateUiState
                ),
                // other
                phoneNumber = argsPhoneNumber,
                verificationCode = verificationCode,
                isLoginSucceed = loadStateUiState.isSuccess()
            )
        }

    // 登录
    fun login() {
        if (checkVerificationCodeValid()) {
            // 检查成功，请求异步获取数据。
            requestAsync {
                // 登录
                loginRepository.loginByPhoneNumberAndVerificationCode(
                    phoneNumber.toString(),
                    verificationCode.value.toString()
                )
            }
        }
    }
}

data class LoginByPhoneNumberAndVerificationCodeNextUiState(
    val baseLoginUiState: BaseLoginUiState = BaseLoginUiState(),
    val phoneNumber: String? = null,
    val verificationCode: String? = null,
    val isLoginSucceed: Boolean = false,
)