package com.bytedance.douyin.core.test

import com.bytedance.core.test.TestFragment
import com.bytedance.douyin.core.common.interfaces.OnFragmentBackgroundListener
import com.bytedance.douyin.core.common.util.setStatusBarDarkFont

/**
 * 描述：
 *
 * @author zhangrq
 * createTime 2024/10/17 17:59
 */
class AppTestFragment : TestFragment(), OnFragmentBackgroundListener {
    override var isBackgroundBright = true
    override fun onResume() {
        super.onResume()
        setStatusBarDarkFont(isDarkFont = isBackgroundBright)
    }

    companion object {

        fun newInstance(contentResId: Int) = AppTestFragment().apply { setArgs(contentResId) }
        fun newInstance(content: String) = AppTestFragment().apply { setArgs(content) }
    }
}