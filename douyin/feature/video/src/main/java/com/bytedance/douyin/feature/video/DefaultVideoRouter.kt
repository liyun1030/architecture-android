package com.bytedance.douyin.feature.video

import androidx.fragment.app.Fragment
import com.bytedance.douyin.core.router.interfaces.VideoRouter
import com.bytedance.douyin.feature.video.ui.video.VideoFragment

/**
 * 描述：
 *
 * @author zhangrq
 * createTime 2024/4/1 下午5:15
 */
class DefaultVideoRouter : VideoRouter {
    override fun createVideoFragment(): Fragment = VideoFragment.newInstance()
}