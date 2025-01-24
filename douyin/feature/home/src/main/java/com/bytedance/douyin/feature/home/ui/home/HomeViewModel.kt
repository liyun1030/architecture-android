package com.bytedance.douyin.feature.home.ui.home

import androidx.lifecycle.SavedStateHandle
import com.bytedance.douyin.core.architecture.app.AppViewModel
import com.bytedance.douyin.core.data.repository.interfaces.HomeRepository
import com.bytedance.douyin.feature.home.util.toHomeTabTitle
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import com.bytedance.douyin.feature.home.ui.home.HomeUiState as UiState

/**
 * 描述：
 *
 * @author zhangrq
 * createTime 2024/8/22 上午9:48
 */
@HiltViewModel
class HomeViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle, homeRepository: HomeRepository,
) : AppViewModel<UiState>() {

    private val homeTabs = homeRepository.getHomeTabsStream().map {
        it.map { type -> HomeTabsItem(type.toHomeTabTitle(), type) }
    }

    // 默认Tab为最后一个
    private val selectedTabIndex = savedStateHandle.getStateFlow(STATE_KEY_SELECTED_TAB_INDEX, -1)

    // UiState默认值，应该传递homeTabs、selectedTabIndex以进行恢复，但是没有homeTabs，此selectedTabIndex也没有用，所以在此可以不传入。
    override val uiStateInitialValue: UiState = UiState()

    override val uiStateFlow: Flow<UiState> = combine(
        homeTabs, selectedTabIndex
    ) { homeTabs, selectedTabIndex ->
        UiState(tabs = homeTabs, selectedTabIndex = selectedTabIndex)
    }

    // selectedTabIndex改变
    fun selectedTabIndexChanged(selectedTabIndex: Int) {
        savedStateHandle[STATE_KEY_SELECTED_TAB_INDEX] = selectedTabIndex
    }
}

private const val STATE_KEY_SELECTED_TAB_INDEX = "state_key_selected_tab_index"

// Home-UiState
data class HomeUiState(
    val tabs: List<HomeTabsItem>? = null,
    val selectedTabIndex: Int = -1,
)