package com.bytedance.douyin.core.common.result

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart

/**
 * 描述：
 *
 * @author zhangrq
 * createTime 2024/3/13 上午10:48
 */
sealed interface Result<out T> {
    data class Success<T>(val data: T) : Result<T>

    data class Error(val exception: Throwable) : Result<Nothing>

    data object Loading : Result<Nothing>
}

// 转为Result类，包含了：开始为Loading、异常为Error、成功为Success。
fun <T> Flow<T>.asResult(): Flow<Result<T>> =
    map<T, Result<T>> { Result.Success(it) }
        .onStart { emit(Result.Loading) }
        .catch { emit(Result.Error(it)) }