package com.bytedance.douyin.core.designsystem.util

import com.bytedance.core.common.exception.RuleException
import com.bytedance.core.common.util.AppProvider
import com.bytedance.douyin.core.designsystem.BuildConfig
import com.bytedance.douyin.core.designsystem.R
import java.net.SocketTimeoutException
import java.net.UnknownHostException

/**
 * 描述：网络异常相关
 *
 * @author zhangrq
 * createTime 2024/11/26 上午10:16
 */
fun Throwable.toErrorMessage(): String {

    return when (this) {
        // 规则异常
        is RuleException -> message.toString()
        else -> {
            val resources = AppProvider.getApplicationContext().resources

            if (BuildConfig.DEBUG) {
                // 详细错误提示
                when (this) {
                    is SocketTimeoutException -> resources.getString(R.string.douyin_core_designsystem_state_error_network_timeout)
                    is UnknownHostException -> resources.getString(R.string.douyin_core_designsystem_state_error_network_unknown_host)
                    else -> resources.getString(
                        R.string.douyin_core_designsystem_state_error_network_unknown_exception,
                        message.toString()
                    )
                }
            } else {
                // 统一错误提示
                resources.getString(R.string.douyin_core_designsystem_state_error_hint)
            }
        }
    }
}