package com.bytedance.core.architecture.message.collector

import android.app.Activity
import android.app.Application
import android.view.View
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.repeatOnLifecycle
import com.bytedance.core.architecture.message.MessageCollector
import com.bytedance.core.architecture.message.MessageController
import com.bytedance.core.common.interfaces.empty.DefaultActivityLifecycleCallbacks
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * 描述：消息收集者-Snakbar（View）实现
 *
 * @author zhangrq
 * createTime 2024/8/14 下午4:01
 */
class SnakbarViewMessageCollector(private val view: View?) : MessageCollector {

    private var currentActivity: Activity? = null

    override fun initCollect(
        messageController: MessageController,
        coroutineScope: CoroutineScope,
        lifecycle: Lifecycle,
    ) {
        coroutineScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                messagesCollect(messageController, { view!! }, coroutineScope)
            }
        }
    }

    /**
     *  全局Snackbar，App在后台不展示。
     *  说明：全局的收集，不会因屏幕翻转、回到后台，而取消收集。
     */
    override fun initGlobalCollect(application: Application, messageController: MessageController) {
        // 创建全局的CoroutineScope，展示Snackbar需要在Main线程。
        val coroutineScope = CoroutineScope(Dispatchers.Main)
        coroutineScope.launch {
            messagesCollect(
                messageController,
                // 先用View的，View没有则用Activity的。
                { view ?: currentActivity?.findViewById(android.R.id.content) },
                coroutineScope
            )
        }
        initAppForegroundActivity(application)
    }

    private fun initAppForegroundActivity(application: Application) {
        application.registerActivityLifecycleCallbacks(object :
            DefaultActivityLifecycleCallbacks() {

            override fun onActivityResumed(activity: Activity) {
                currentActivity = activity
            }
        })
    }


    private suspend fun messagesCollect(
        messageController: MessageController, view: () -> View?, coroutineScope: CoroutineScope,
    ) {
        messageController.messages.collect {
            if (it != null) {
                showSnackbar(view(), coroutineScope, it.message, it.isShort) {
                    messageController.setMessageShown(it.id)
                }
            }
        }
    }

    private suspend fun showSnackbar(
        view: View?,
        coroutineScope: CoroutineScope,
        message: Any,
        isShort: Boolean,
        snackbarShown: () -> Unit,
    ) {
        view ?: return
        var snackbar: Snackbar? = null
        var isSnackbarShown = false
        try {
            val duration = if (isShort) Snackbar.LENGTH_SHORT else Snackbar.LENGTH_LONG
            snackbar = when (message) {
                is Int -> {
                    Snackbar.make(
                        view, message, duration
                    )
                }

                is String -> {
                    Snackbar.make(
                        view, message, duration
                    )
                }

                else -> null
            }
            // 回调
            snackbar?.addCallback(object : BaseTransientBottomBar.BaseCallback<Snackbar>() {

                override fun onShown(transientBottomBar: Snackbar?) {
                    super.onShown(transientBottomBar)
                    isSnackbarShown = true
                    // 通知成功
//                    snackbarShown()
                    coroutineScope.launch {
                        delay(DURATION_SHOWN)
                        snackbarShown()
                    }
                }
            })
            // 展示
            snackbar?.show()
            // Snackbar增加耗时，为了解决展示太短导致Snackbar瞬态问题。
            delay(if (isShort) DURATION_SHORT else DURATION_LONG)
            // 解决多个展示冲突问题，导致没展示成功，在此循环展示。
            while (!isSnackbarShown) {
                snackbar?.show()
                delay(2000)
            }
        } catch (e: Exception) {
            e.printStackTrace()
            // 屏幕翻转、回到后台导致的取消异常，直接取消。
            snackbar?.dismiss()
        }
    }

    companion object {
        // 至少展示此时间，才代表已经展示成功。
        private const val DURATION_SHOWN = 1000L

        // 至少展示以下时间，才能继续展示下个。
        private const val DURATION_SHORT = 2000L
        private const val DURATION_LONG = 3500L
    }
}