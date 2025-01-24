package com.bytedance.douyin.feature.video.ui.videoitem

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import com.bytedance.douyin.core.architecture.app.views.AppViewsEmptyViewModelFragment
import com.bytedance.douyin.feature.video.databinding.DouyinFeatureVideoFragmentVideoItemBinding as ViewBinding

/**
 * 描述：
 *
 * @author zhangrq
 * createTime 2024/4/2 下午3:33
 */
class VideoItemFragment : AppViewsEmptyViewModelFragment<ViewBinding>() {
    override fun inflateViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
    ) = ViewBinding.inflate(inflater, container, false)

    override fun ViewBinding.initViews() {
        title.text = arguments?.getLong(KEY_ID).toString()
    }

    override fun ViewBinding.initListeners() {

    }

    override fun ViewBinding.initObservers() {

    }

    companion object {
        private const val KEY_ID = "id"
        internal fun newInstance(id: Long) = VideoItemFragment().apply {
            arguments = Bundle().apply {
                putLong(KEY_ID, id)
            }
        }
    }
}