package com.bytedance.douyin.feature.login.ui.login.email

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.viewModels
import com.bytedance.douyin.feature.login.ui.base.BaseLoginViewsFragment
import dagger.hilt.android.AndroidEntryPoint
import com.bytedance.douyin.feature.login.databinding.DouyinFeatureLoginFragmentLoginByEmailAndPasswordBinding as ViewBinding
import com.bytedance.douyin.feature.login.ui.login.email.LoginByEmailAndPasswordUiState as UiState
import com.bytedance.douyin.feature.login.ui.login.email.LoginByEmailAndPasswordViewModel as ViewModel

/**
 * 描述：登录-邮箱+密码
 *
 * @author zhangrq
 * createTime 2024/4/9 下午4:03
 */
@AndroidEntryPoint
class LoginByEmailAndPasswordFragment : BaseLoginViewsFragment<ViewBinding, UiState, ViewModel>() {
    override val viewModel: ViewModel by viewModels()

    override fun inflateViewBinding(inflater: LayoutInflater, container: ViewGroup?) =
        ViewBinding.inflate(inflater, container, false)

    override fun ViewBinding.initViews() {
        // 协议
        initAgreement(agreement, false)
    }

    override fun ViewBinding.initListeners() {
        // 帮助与设置
        titleBar.setRightClick { startHelpAndSetup() }
        // 邮箱
        email.doAfterTextChanged { viewModel.emailChanged(it.toString()) }
        // 密码
        password.doAfterTextChanged { viewModel.passwordChanged(it.toString()) }
        // 协议
        agreement.setOnCheckedChangeListener { _, isChecked ->
            viewModel.agreementIsCheckedChanged(isChecked)
        }
        // 登录
        login.setOnClickListener { viewModel.login() }
        // 手机号密码登录
        loginPhoneNumberAndPassword.setOnClickListener { navigateUp() }
    }

    override fun ViewBinding.initObservers() {
    }

    override fun ViewBinding.onUiStateCollect(uiState: UiState) {
        // 处理通用的
        handleBaseLoginUiState(uiState.baseLoginUiState, error, login)
        // 邮箱（view层原生组件可省略赋值）
        email.setTextKeepState(uiState.email ?: "")
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