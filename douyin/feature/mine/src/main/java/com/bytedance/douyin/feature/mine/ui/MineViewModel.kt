package com.bytedance.douyin.feature.mine.ui

import androidx.lifecycle.viewModelScope
import com.bytedance.douyin.core.architecture.app.AppViewModel
import com.bytedance.douyin.core.datastore.AppPreferencesDataSource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject
import com.bytedance.douyin.feature.mine.ui.MineUiState as UiState

/**
 * 描述：
 *
 * @author zhangrq
 * createTime 2024/12/25 15:11
 */
@HiltViewModel
class MineViewModel @Inject constructor(
    // 此应该放到Repository类，在此为了方便，就没加了。
    private val preferencesDataSource: AppPreferencesDataSource,
) : AppViewModel<UiState>() {
    override val uiStateInitialValue: UiState = UiState()

    override val uiStateFlow: Flow<UiState> =
        preferencesDataSource.userData.map { it.useDynamicColor }.distinctUntilChanged().map {
            UiState(useDynamicColor = it)
        }

    fun updateDynamicColorPreference(useDynamicColor: Boolean, finish: () -> Unit) {
        if (useDynamicColor == uiState.value.useDynamicColor) return // 去重
        viewModelScope.launch {
            preferencesDataSource.setDynamicColorPreference(useDynamicColor)
            delay(250) // 延时，以让开关动画执行完后重建页面。
            finish()
        }
    }
}

// Mine-UiState
data class MineUiState(
    val useDynamicColor: Boolean = false,
)