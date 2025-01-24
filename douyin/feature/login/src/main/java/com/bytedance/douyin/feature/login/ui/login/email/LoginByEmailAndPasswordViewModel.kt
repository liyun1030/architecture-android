package com.bytedance.douyin.feature.login.ui.login.email

import androidx.lifecycle.SavedStateHandle
import com.bytedance.core.architecture.stateview.isSuccess
import com.bytedance.douyin.core.data.repository.interfaces.LoginRepository
import com.bytedance.douyin.feature.login.ui.base.BaseLoginUiState
import com.bytedance.douyin.feature.login.ui.base.BaseLoginViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import javax.inject.Inject
import com.bytedance.douyin.feature.login.ui.login.email.LoginByEmailAndPasswordUiState as UiState

/**
 * 描述：
 *
 * @author zhangrq
 * createTime 2024/9/3 上午10:53
 */
@HiltViewModel
class LoginByEmailAndPasswordViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val loginRepository: LoginRepository,
) : BaseLoginViewModel<UiState>(savedStateHandle) {

    // UiState初始化值
    override val uiStateInitialValue: UiState = UiState(
        email = email.value,
        password = password.value,
        isAgreementChecked = isAgreementChecked.value
    )

    // UiState的Flow
    override val uiStateFlow: Flow<UiState> = combine(
        isAgreementChecked, email, password, loadStateUiState
    ) { isAgreementChecked, email, password, loadStateUiState ->
        // 转UiState
        UiState(
            // base
            baseLoginUiState = createBaseLoginUiState(
                isAgreementChecked && isEmailValid() && isPasswordValid(),
                loadStateUiState
            ),
            // other
            email = email,
            password = password,
            isAgreementChecked = isAgreementChecked,
            isLoginSucceed = loadStateUiState.isSuccess()
        )
    }

    fun login() {
        if (checkEmailValid() && checkPasswordCodeValid() && checkAgreementChecked()) {
            // 检查成功，请求异步获取数据。
            requestAsync {
                // 登录
                loginRepository.loginByEmailAndPassword(
                    email.value.toString(),
                    password.value.toString()
                )
            }
        }
    }
}

data class LoginByEmailAndPasswordUiState(
    val baseLoginUiState: BaseLoginUiState = BaseLoginUiState(),
    val email: String? = null,
    val password: String? = null,
    val isAgreementChecked: Boolean = false,
    val isLoginSucceed: Boolean = false,
)