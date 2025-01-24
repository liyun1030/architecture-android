package com.bytedance.douyin.feature.login.ui.login.password

import androidx.lifecycle.SavedStateHandle
import com.bytedance.core.architecture.stateview.isSuccess
import com.bytedance.douyin.core.data.repository.interfaces.LoginRepository
import com.bytedance.douyin.feature.login.ui.base.BaseLoginUiState
import com.bytedance.douyin.feature.login.ui.base.BaseLoginViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import javax.inject.Inject
import com.bytedance.douyin.feature.login.ui.login.password.LoginByPhoneNumberAndPasswordUiState as UiState

/**
 * 描述：
 *
 * @author zhangrq
 * createTime 2024/9/2 下午3:13
 */
@HiltViewModel
class LoginByPhoneNumberAndPasswordViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val loginRepository: LoginRepository,
) : BaseLoginViewModel<UiState>(savedStateHandle) {

    // UiState初始化值
    override val uiStateInitialValue: UiState = UiState(
        phoneNumber = phoneNumber.value,
        password = password.value,
        isAgreementChecked = isAgreementChecked.value
    )

    // UiState的Flow
    override val uiStateFlow: Flow<UiState> = combine(
        isAgreementChecked, phoneNumber, password, loadStateUiState
    ) { isAgreementChecked, phoneNumber, password, loadStateUiState ->
        // 转UiState
        UiState(
            // base
            baseLoginUiState = createBaseLoginUiState(
                isAgreementChecked && isPhoneNumberValid() && isPasswordValid(),
                loadStateUiState
            ),
            // other
            phoneNumber = phoneNumber,
            password = password,
            isAgreementChecked = isAgreementChecked,
            isLoginSucceed = loadStateUiState.isSuccess()
        )
    }

    fun login() {
        if (checkPhoneNumberValid() && checkPasswordCodeValid() && checkAgreementChecked()) {
            // 检查成功，请求异步获取数据。
            requestAsync {
                // 登录
                loginRepository.loginByPhoneNumberAndPassword(
                    phoneNumber.value.toString(),
                    password.value.toString()
                )
            }
        }
    }
}

data class LoginByPhoneNumberAndPasswordUiState(
    val baseLoginUiState: BaseLoginUiState = BaseLoginUiState(),
    val phoneNumber: String? = null,
    val password: String? = null,
    val isAgreementChecked: Boolean = false,
    val isLoginSucceed: Boolean = false,
)