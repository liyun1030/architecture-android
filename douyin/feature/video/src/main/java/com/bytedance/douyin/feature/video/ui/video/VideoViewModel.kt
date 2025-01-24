package com.bytedance.douyin.feature.video.ui.video

import com.bytedance.douyin.core.architecture.app.AppViewModel
import com.bytedance.douyin.core.model.VideoItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import com.bytedance.douyin.feature.video.ui.video.VideoUiState as UiState

/**
 * 描述：
 *
 * @author zhangrq
 * createTime 2024/12/24 11:10
 */
@HiltViewModel
class VideoViewModel @Inject constructor() : AppViewModel<UiState>() {
    override val uiStateInitialValue = UiState()

    override val uiStateFlow: Flow<UiState> = flow {
        emit(getData())
    }.map {
        UiState(it)
    }

    private fun getData(): List<VideoItem> {
        val list = mutableListOf<VideoItem>()
        for (i in 0..100) {
            list.add(VideoItem(id = i.toLong()))
        }
        return list
    }
}

// Video-UiState
data class VideoUiState(
    val list: List<VideoItem>? = null,
)