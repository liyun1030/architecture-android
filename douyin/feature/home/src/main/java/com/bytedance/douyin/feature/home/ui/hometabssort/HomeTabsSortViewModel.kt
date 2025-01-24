package com.bytedance.douyin.feature.home.ui.hometabssort

import com.bytedance.douyin.core.architecture.app.AppViewModel
import com.bytedance.douyin.core.data.repository.interfaces.HomeTabsSortRepository
import com.bytedance.douyin.feature.home.util.toHomeTabTitle
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import com.bytedance.douyin.feature.home.ui.hometabssort.HomeTabsSortUiState as UiState

/**
 * 描述：
 *
 * @author zhangrq
 * createTime 2024/10/24 11:36
 */
@HiltViewModel
class HomeTabsSortViewModel @Inject constructor(
    private val homeTabsSortRepository: HomeTabsSortRepository,
) : AppViewModel<UiState>() {

    private val homeTabs = homeTabsSortRepository.getHomeTabsCacheStream().map {
        it.map { type -> HomeTabsSortItem(type.toHomeTabTitle(), type) }
    }

    override val uiStateInitialValue: UiState = UiState()

    override val uiStateFlow: Flow<UiState> = homeTabs.map { homeTabs ->
        UiState(tabs = homeTabs)
    }

    // 移动
    fun move(from: Int, to: Int) {
        homeTabsSortRepository.move(from, to)
    }

    // 保存
    fun save() {
        homeTabsSortRepository.save()
    }
}

// HomeTabsSort-UiState
data class HomeTabsSortUiState(
    val tabs: List<HomeTabsSortItem>? = null,
)