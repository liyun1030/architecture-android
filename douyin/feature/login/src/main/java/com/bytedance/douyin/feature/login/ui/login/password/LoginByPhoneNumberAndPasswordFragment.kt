package com.bytedance.douyin.feature.login.ui.login.password

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.viewModels
import com.bytedance.douyin.core.designsystem.util.NoUnderlineClickableSpan
import com.bytedance.douyin.feature.login.R
import com.bytedance.douyin.feature.login.ui.base.BaseLoginViewsFragment
import com.zrq.spanbuilder.Spans
import dagger.hilt.android.AndroidEntryPoint
import com.bytedance.douyin.feature.login.databinding.DouyinFeatureLoginFragmentLoginByPhoneNumberAndPasswordBinding as ViewBinding
import com.bytedance.douyin.feature.login.ui.login.password.LoginByPhoneNumberAndPasswordFragmentDirections as Directions
import com.bytedance.douyin.feature.login.ui.login.password.LoginByPhoneNumberAndPasswordUiState as UiState
import com.bytedance.douyin.feature.login.ui.login.password.LoginByPhoneNumberAndPasswordViewModel as ViewModel


/**
 * 描述：登录-手机号+密码
 *
 * @author zhangrq
 * createTime 2024/4/9 下午4:03
 */
@AndroidEntryPoint
class LoginByPhoneNumberAndPasswordFragment :
    BaseLoginViewsFragment<ViewBinding, UiState, ViewModel>() {
    override val viewModel: ViewModel by viewModels()

    override fun inflateViewBinding(inflater: LayoutInflater, container: ViewGroup?) =
        ViewBinding.inflate(inflater, container, false)

    override fun ViewBinding.initViews() {
        // 协议
        initAgreement(agreement, false)
        // 忘记了？找回密码
        retrievePassword.text = Spans.builder()
            .text(resources.getText(R.string.douyin_feature_login_phone_number_and_password_retrieve_password_1))
            .text(resources.getText(R.string.douyin_feature_login_phone_number_and_password_retrieve_password_2))
            .click(retrievePassword, NoUnderlineClickableSpan {
                navigateToRetrievePassword(phoneNumber.text.toString())
            }).build()
    }

    override fun ViewBinding.initListeners() {
        // 帮助与设置
        titleBar.setRightClick { startHelpAndSetup() }
        // 手机号
        phoneNumber.doAfterTextChanged { viewModel.phoneNumberChanged(it.toString()) }
        // 密码
        password.doAfterTextChanged { viewModel.passwordChanged(it.toString()) }
        // 协议
        agreement.setOnCheckedChangeListener { _, isChecked ->
            viewModel.agreementIsCheckedChanged(isChecked)
        }
        // 登录
        login.setOnClickListener { viewModel.login() }
        // 邮箱密码登录
        loginEmailAndPassword.setOnClickListener { navigate(Directions.actionToLoginByEmailAndPassword()) }
    }

    override fun ViewBinding.initObservers() {
    }

    override fun ViewBinding.onUiStateCollect(uiState: UiState) {
        // 处理通用的
        handleBaseLoginUiState(uiState.baseLoginUiState, error, login)
        // 手机号（view层原生组件可省略赋值）
        phoneNumber.setTextKeepState(uiState.phoneNumber ?: "")
        // 密码（view层原生组件可省略赋值）
        password.setTextKeepState(uiState.password ?: "")
        // 协议（view层原生组件可省略赋值）
        agreement.isChecked = uiState.isAgreementChecked

        // 成功
        if (uiState.isLoginSucceed) {
            // 销毁
            requireActivity().finish()
        }
    }
}