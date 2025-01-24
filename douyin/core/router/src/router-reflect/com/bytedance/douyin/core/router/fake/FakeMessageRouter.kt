package com.bytedance.douyin.core.router.fake

import androidx.fragment.app.Fragment
import com.bytedance.douyin.core.router.interfaces.MessageRouter
import com.bytedance.douyin.core.test.AppTestFragment

/**
 * 描述：
 *
 * @author zhangrq
 * createTime 2024/3/19 下午5:15
 */
class FakeMessageRouter : MessageRouter {
    override fun createMessageFragment(): Fragment = AppTestFragment.newInstance("Message")
}