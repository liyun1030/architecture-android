package com.bytedance.douyin.core.test

import com.bytedance.core.test.TestListFragment

/**
 * 描述：
 *
 * @author zhangrq
 * createTime 2024/10/23 14:39
 */
class AppTestListFragment : TestListFragment() {

    companion object {
        fun newInstance(count: Int = 100) = AppTestListFragment().apply { setArgs(count) }
    }
}