package com.bytedance.douyin.core.webview

import android.app.Activity
import com.bytedance.core.architecture.message.MessageManager
import com.bytedance.core.webview.WebViewActivity

/**
 * 描述：
 *
 * @author zhangrq
 * createTime 2024/7/1 上午11:10
 */
object WebViewRouter {

    /**
     * 用户协议
     */
    fun startUserAgreement(activity: Activity, titleResId: Int) {
        MessageManager.showGlobalMessage(titleResId)
        WebViewActivity.startActivity(activity, "https://www.baidu.com")
    }

    /**
     * 隐私政策
     */
    fun startPrivacyPolicy(activity: Activity, titleResId: Int) {
        MessageManager.showGlobalMessage(titleResId)
        WebViewActivity.startActivity(activity, "https://www.baidu.com")
    }

    /**
     * 运营商服务协议
     */
    fun startCarrierServiceAgreement(activity: Activity, titleResId: Int) {
        MessageManager.showGlobalMessage(titleResId)
        WebViewActivity.startActivity(activity, "https://www.baidu.com")
    }

    /**
     * 帮助与设置
     *
     */
    fun startHelpAndSetup(activity: Activity, titleResId: Int) {
        MessageManager.showGlobalMessage(titleResId)
        WebViewActivity.startActivity(activity, "https://www.baidu.com")
    }

    /**
     * 找回帐号
     *
     */
    fun startRetrieveAccount(activity: Activity, titleResId: Int) {
        MessageManager.showGlobalMessage(titleResId)
        WebViewActivity.startActivity(activity, "https://www.baidu.com")
    }
}