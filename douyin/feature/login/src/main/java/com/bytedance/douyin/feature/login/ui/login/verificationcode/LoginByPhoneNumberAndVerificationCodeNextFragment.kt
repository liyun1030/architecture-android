package com.bytedance.douyin.feature.login.ui.login.verificationcode

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.viewModels
import com.bytedance.douyin.core.designsystem.util.NoUnderlineClickableSpan
import com.bytedance.douyin.feature.login.R
import com.bytedance.douyin.feature.login.ui.base.BaseLoginViewsFragment
import com.zrq.spanbuilder.Spans
import dagger.hilt.android.AndroidEntryPoint
import com.bytedance.douyin.feature.login.databinding.DouyinFeatureLoginFragmentLoginByPhoneNumberAndVerificationCodeNextBinding as ViewBinding
import com.bytedance.douyin.feature.login.ui.login.verificationcode.LoginByPhoneNumberAndVerificationCodeNextUiState as UiState
import com.bytedance.douyin.feature.login.ui.login.verificationcode.LoginByPhoneNumberAndVerificationCodeNextViewModel as ViewModel

/**
 * 描述：登录-手机号+验证码-下一步
 *
 * @author zhangrq
 * createTime 2024/4/9 下午4:03
 */
@AndroidEntryPoint
class LoginByPhoneNumberAndVerificationCodeNextFragment :
    BaseLoginViewsFragment<ViewBinding, UiState, ViewModel>() {
    override val viewModel: ViewModel by viewModels()

    override fun inflateViewBinding(inflater: LayoutInflater, container: ViewGroup?) =
        ViewBinding.inflate(inflater, container, false)

    override fun ViewBinding.initViews() {
        // 语音验证码
        voiceVerificationCode.text = Spans.builder()
            .text(resources.getText(R.string.douyin_feature_login_phone_number_and_verification_code_next_voice_verification_code_1))
            .text(resources.getText(R.string.douyin_feature_login_phone_number_and_verification_code_next_voice_verification_code_2))
            .click(voiceVerificationCode, NoUnderlineClickableSpan {
                viewModel.showMessage(R.string.douyin_feature_login_phone_number_and_verification_code_next_voice_verification_code_2)
            }).build()
    }

    override fun ViewBinding.initListeners() {
        // 帮助与设置
        titleBar.setRightClick { startHelpAndSetup() }
        // 验证码
        verificationCode.doAfterTextChanged { viewModel.verificationCodeChanged(it.toString()) }
        // 登录
        login.setOnClickListener { viewModel.login() }
    }

    override fun ViewBinding.initObservers() {
        // 初始化验证码已发送
        initVerificationCodeSent(subtitle) { it.phoneNumber }
    }

    override fun ViewBinding.onUiStateCollect(uiState: UiState) {
        // 处理通用的
        handleBaseLoginUiState(uiState.baseLoginUiState, error, login)
        // 验证码（view层原生组件可省略赋值）
        verificationCode.setTextKeepState(uiState.verificationCode ?: "")
        // 成功
        if (uiState.isLoginSucceed) {
            // 销毁
            requireActivity().finish()
        }
    }
}