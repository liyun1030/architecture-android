package com.bytedance.douyin.app.main

import androidx.lifecycle.viewModelScope
import com.bytedance.douyin.R
import com.bytedance.douyin.core.architecture.app.AppViewModel
import com.bytedance.douyin.core.data.repository.interfaces.MainRepository
import com.bytedance.douyin.core.model.MainTabType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject
import com.bytedance.douyin.app.main.MainUiState as UiState

/**
 * 描述：
 *
 * @author zhangrq
 * createTime 2024/3/8 下午3:18
 */
@HiltViewModel
class MainViewModel @Inject constructor(
    mainRepository: MainRepository,
) : AppViewModel<UiState>() {

    override val uiStateInitialValue: UiState = UiState()

    override val uiStateFlow: Flow<UiState> = mainRepository.getMainTabsStream().map {
        it.map { type ->
            val title = when (type) {
                MainTabType.HOME -> R.string.main_tabs_home
                MainTabType.FRIEND -> R.string.main_tabs_friend
                MainTabType.CAMERA -> R.string.main_tabs_camera
                MainTabType.MESSAGE -> R.string.main_tabs_message
                MainTabType.MINE -> R.string.main_tabs_mine
                MainTabType.SHOP -> R.string.main_tabs_shop
            }
            MainTabsItem(title, type) {
                viewModelScope.launch {
                    mainRepository.updateMainTabShopOrFriend(!it)
                }
            }
        }
    }.map {
        UiState(tabs = it)
    }
}

// Main-UiState
data class MainUiState(
    val tabs: List<MainTabsItem>? = null,
)