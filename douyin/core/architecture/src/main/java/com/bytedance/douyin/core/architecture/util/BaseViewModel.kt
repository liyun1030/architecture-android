package com.bytedance.douyin.core.architecture.util

import android.view.View
import androidx.lifecycle.viewModelScope
import com.bytedance.core.architecture.base.BaseViewModel
import com.bytedance.core.architecture.stateview.LoadStateUiState
import com.bytedance.douyin.core.designsystem.util.toErrorMessage
import kotlinx.coroutines.launch

/**
 * 描述：
 *
 * @author zhangrq
 * createTime 2024/12/18 10:17
 */
/**
 * 请求异步-通用
 */
fun <Data> BaseViewModel<*>.requestAsyncBase(
    isHideContentAllStateDefault: Boolean? = null,
    isShowAllStateDefault: Boolean = true,
    // loading
    isLoadingHideContent: Boolean? = isHideContentAllStateDefault,
    isShowLoading: Boolean = isShowAllStateDefault,
    isLoadingDialog: Boolean = false,
    loadingCallback: (LoadStateUiState.Loading.() -> Unit)? = null,
    // error
    isErrorHideContent: Boolean? = isHideContentAllStateDefault,
    isShowError: Boolean = isShowAllStateDefault,
    errorCallback: (LoadStateUiState.Error.() -> Unit)? = null,
    // empty
    isErrorEmpty: Throwable.() -> Boolean = { false }, // 判断Error下什么情况下为空，默认不支持为空，可使用isEmptyError()统一封装。
    isSuccessEmpty: Data.() -> Boolean = { false }, // 判断Success下什么情况下为空，默认不支持为空。
    isEmptyHideContent: Boolean? = isHideContentAllStateDefault,
    isShowEmpty: Boolean = isShowAllStateDefault,
    emptyCallback: (LoadStateUiState.Empty.() -> Unit)? = null,
    // success
    successCallback: (LoadStateUiState.Success.() -> Unit)? = null,

    asyncFun: suspend () -> Data,
) {
    viewModelScope.launch {
        // Loading
        val loading =
            LoadStateUiState.Loading(isLoadingHideContent, isShowLoading, isLoadingDialog)
        loadStateUiState.value = loading
        loadingCallback?.invoke(loading)
        try {
            val data = asyncFun()
            if (isSuccessEmpty(data)) {
                // Empty
                val empty = LoadStateUiState.Empty(isEmptyHideContent, isShowEmpty)
                loadStateUiState.value = empty
                emptyCallback?.invoke(empty)
            } else {
                // Success
                val success =
                    LoadStateUiState.Success(if (isLoadingHideContent == null || isErrorHideContent == null || isEmptyHideContent == null) null else false)
                loadStateUiState.value = success
                successCallback?.invoke(success)
            }
        } catch (e: Exception) {
            e.printStackTrace()
            if (isErrorEmpty(e)) {
                // Empty
                val empty = LoadStateUiState.Empty(isEmptyHideContent, isShowEmpty)
                loadStateUiState.value = empty
                emptyCallback?.invoke(empty)
            } else {
                // Error
                // 重试
                val retry: (v: View) -> Unit = {
                    requestAsyncBase(
                        isHideContentAllStateDefault,
                        isShowAllStateDefault,
                        // loading
                        isLoadingHideContent,
                        isShowLoading,
                        isLoadingDialog,
                        loadingCallback,
                        // error
                        isErrorHideContent,
                        isShowError,
                        errorCallback,
                        // empty
                        isErrorEmpty,
                        isSuccessEmpty,
                        isEmptyHideContent,
                        isShowEmpty,
                        emptyCallback,
                        // success
                        successCallback,
                        asyncFun
                    )
                }
                val error = LoadStateUiState.Error(isErrorHideContent, isShowError, e, retry)
                loadStateUiState.value = error
                errorCallback?.invoke(error)
            }
        }
    }
}

/**
 * 请求异步-仅提示。即：不隐藏内容，仅展示加载中，加载失败改为展示消息提示。
 */
fun BaseViewModel<*>.requestAsyncOnlyHint(
    asyncFun: suspend () -> Unit,
) {
    // 效果说明：不隐藏内容，仅展示加载中，加载失败改为展示消息提示。
    requestAsyncBase(
        isShowAllStateDefault = false,
        isShowLoading = true,
        errorCallback = { showMessage(error.toErrorMessage()) },
        asyncFun = asyncFun
    )
}

/**
 * 请求异步-展示所有状态。即：隐藏内容，展示所有状态，展示Loading时不隐藏内容。
 */
fun <Data> BaseViewModel<*>.requestAsyncShowAllState(
    asyncFun: suspend () -> Data,
) {
    // 效果说明：隐藏内容，展示所有状态，展示Loading时不隐藏内容。
    requestAsyncBase(
        // 成功
        isHideContentAllStateDefault = true,
        isLoadingHideContent = null,
        asyncFun = asyncFun
    )
}