package com.bytedance.douyin.core.common.network

import javax.inject.Qualifier

/**
 * 描述：
 *
 * @author zhangrq
 * createTime 2024/3/13 上午10:48
 */
@Qualifier
@Retention(AnnotationRetention.RUNTIME)
// 线程调度程序（Dispatcher）的限定符，用于标记使用哪个线程调度程序。
annotation class Dispatcher(val appDispatcher: AppDispatchers)

enum class AppDispatchers {
    Default,
    IO,
}
