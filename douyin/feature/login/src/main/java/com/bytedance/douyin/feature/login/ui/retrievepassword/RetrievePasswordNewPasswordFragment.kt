package com.bytedance.douyin.feature.login.ui.retrievepassword

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.viewModels
import com.bytedance.douyin.feature.login.ui.base.BaseLoginViewsFragment
import dagger.hilt.android.AndroidEntryPoint
import com.bytedance.douyin.feature.login.databinding.DouyinFeatureLoginFragmentRetrievePasswordNewPasswordBinding as ViewBinding
import com.bytedance.douyin.feature.login.ui.retrievepassword.RetrievePasswordNewPasswordUiState as UiState
import com.bytedance.douyin.feature.login.ui.retrievepassword.RetrievePasswordNewPasswordViewModel as ViewModel

/**
 * 描述：找回密码-新密码
 *
 * @author zhangrq
 * createTime 2024/4/9 下午4:03
 */
@AndroidEntryPoint
class RetrievePasswordNewPasswordFragment :
    BaseLoginViewsFragment<ViewBinding, UiState, ViewModel>() {
    override val viewModel: ViewModel by viewModels()

    override fun inflateViewBinding(inflater: LayoutInflater, container: ViewGroup?) =
        ViewBinding.inflate(inflater, container, false)

    override fun ViewBinding.initViews() {
    }

    override fun ViewBinding.initListeners() {
        // 密码
        password.doAfterTextChanged { viewModel.passwordChanged(it.toString()) }
        // 完成
        complete.setOnClickListener { viewModel.complete() }
    }

    override fun ViewBinding.initObservers() {
    }

    override fun ViewBinding.onUiStateCollect(uiState: UiState) {
        // 处理通用的
        handleBaseLoginUiState(uiState.baseLoginUiState, error, complete)
        // 密码（view层原生组件可省略赋值）
        password.setTextKeepState(uiState.password ?: "")

        // 成功
        if (uiState.isRetrievePasswordSucceed) {
            // 销毁
            requireActivity().finish()
        }
    }
}