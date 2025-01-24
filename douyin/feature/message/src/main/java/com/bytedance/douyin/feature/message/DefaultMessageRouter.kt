package com.bytedance.douyin.feature.message

import androidx.fragment.app.Fragment
import com.bytedance.douyin.core.router.interfaces.MessageRouter
import com.bytedance.douyin.feature.message.ui.MessageFragment

/**
 * 描述：
 *
 * @author zhangrq
 * createTime 2024/3/19 下午5:15
 */
class DefaultMessageRouter : MessageRouter {
    override fun createMessageFragment(): Fragment = MessageFragment.newInstance()
}