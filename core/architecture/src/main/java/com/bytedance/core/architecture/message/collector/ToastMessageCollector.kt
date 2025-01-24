package com.bytedance.core.architecture.message.collector

import android.app.Activity
import android.app.Application
import android.content.Context
import android.os.Build
import android.widget.Toast
import android.widget.Toast.Callback
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.repeatOnLifecycle
import com.bytedance.core.architecture.message.MessageCollector
import com.bytedance.core.architecture.message.MessageController
import com.bytedance.core.common.interfaces.empty.DefaultActivityLifecycleCallbacks
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * 描述：消息收集者-Toast实现
 *
 * @author zhangrq
 * createTime 2024/8/12 上午9:20
 */
class ToastMessageCollector(private val context: Context) : MessageCollector {

    private var toast: Toast? = null
    private var isToastShown = false
    private var isToastShort = false

    override fun initCollect(
        messageController: MessageController,
        coroutineScope: CoroutineScope,
        lifecycle: Lifecycle,
    ) {
        coroutineScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                messagesCollect(
                    messageController, context
                )
            }
        }
    }

    /**
     *  全局Toast，在任何位置都会展示，即使App在后台（低版本展示，高版本不展示），也进行展示提示。
     *  注意：高版本Toast在后台展示异常（API-31(android-12)、API-32(android-12L)），或不展示（API-33(android-13)及以上）
     *  说明：全局的收集，不会因屏幕翻转、回到后台，而取消收集。
     */
    override fun initGlobalCollect(application: Application, messageController: MessageController) {
        // 创建全局的CoroutineScope，展示Toast需要在Main线程。
        val coroutineScope = CoroutineScope(Dispatchers.Main)
        coroutineScope.launch {
            messagesCollect(messageController, context)
        }
        initAppForegroundReshow(application, coroutineScope)
    }

    private fun initAppForegroundReshow(
        application: Application, coroutineScope: CoroutineScope,
    ) {
        application.registerActivityLifecycleCallbacks(object :
            DefaultActivityLifecycleCallbacks() {

            override fun onActivityResumed(activity: Activity) {
                coroutineScope.launch {
                    if (!isToastShown) {
                        // 没展示，进行展示。
                        toast?.show()
                        delay(if (isToastShort) DURATION_SHORT else DURATION_LONG)
                    }
                }
            }
        })
    }

    private suspend fun messagesCollect(
        messageController: MessageController,
        context: Context,
    ) {
        messageController.messages.collect {
            if (it != null) {
                showToast(context, it.message, it.isShort) {
                    messageController.setMessageShown(it.id)
                }
            }
        }
    }

    private suspend fun showToast(
        context: Context,
        message: Any,
        isShort: Boolean,
        toastShown: () -> Unit,
    ) =
        // withContext，是为了解决App调用的
        withContext(Dispatchers.Main) {
            // 还原
            toast = null
            isToastShown = false
            isToastShort = isShort
            try {
                val duration = if (isShort) Toast.LENGTH_SHORT else Toast.LENGTH_LONG
                toast = when (message) {
                    is Int -> {
                        Toast.makeText(
                            context,
                            message,
                            duration
                        )
                    }

                    is String -> {
                        Toast.makeText(
                            context,
                            message,
                            duration
                        )
                    }

                    else -> null
                }
                // 使用Callback方式通知成功，是为了解决高版本Toast在后台不展示问题。
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                    // API-30（android-11）及以上，通过回调判断成功。
                    // -回调
                    toast?.addCallback(object : Callback() {

                        override fun onToastShown() {
                            super.onToastShown()
                            isToastShown = true
                            // -通知成功（只要展示，就代表展示成功）。
                            toastShown()
                        }
                    })
                    // -展示
                    toast?.show()
                    // -Toast增加耗时，为了解决频繁展示导致Toast丢失问题。
                    delay(if (isShort) DURATION_SHORT else DURATION_LONG)
                } else {
                    // 低版本，能正常在后台展示，直接通知成功。
                    // -展示
                    toast?.show()
                    // -通知成功（只要展示，就代表展示成功）。
                    toastShown()
                    // -Toast增加耗时，为了解决频繁展示导致Toast丢失问题。
                    delay(if (isShort) DURATION_SHORT else DURATION_LONG)
                }
            } catch (e: Exception) {
                e.printStackTrace()
                // 屏幕翻转、回到后台导致的取消异常，直接取消。
                toast?.cancel()
            }
        }

    companion object {
        // 经过100次的Toast展示测试（防止Toast丢失），短的时间为2400、长的时间为3900，最为合适。
        private const val DURATION_SHORT = 2400L
        private const val DURATION_LONG = 3900L
    }
}