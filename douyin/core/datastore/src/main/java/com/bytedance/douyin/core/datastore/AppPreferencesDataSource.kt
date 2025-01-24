package com.bytedance.douyin.core.datastore

import androidx.datastore.core.DataStore
import com.bytedance.douyin.core.datastore.model.asExternalModel
import com.bytedance.douyin.core.datastore.model.asLocalModel
import com.bytedance.douyin.core.model.HomeTabType
import com.bytedance.douyin.core.model.MainTabType
import com.bytedance.douyin.core.model.User
import com.bytedance.douyin.core.model.UserData
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import com.bytedance.douyin.core.datastore.User as LocalUser

/**
 * 描述：
 *
 * @author zhangrq
 * createTime 2024/3/13 上午10:03
 */
class AppPreferencesDataSource @Inject constructor(
    private val userPreferences: DataStore<UserPreferences>,
) {
    val userData = userPreferences.data.map {
        // 转UserData
        UserData(
            user = it.user.asExternalModel(),
            mainTabs = it.mainTabsList,
            homeTabs = it.homeTabsList,
            useDynamicColor = it.useDynamicColor,
        )
    }

    suspend fun setUser(user: User) {
        userPreferences.updateData {
            it.copy { this.user = user.asLocalModel() }
        }
    }

    suspend fun setMainTabs(tabs: List<MainTabType>) {
        userPreferences.updateData {
            it.copy {
                mainTabs.clear()
                mainTabs.addAll(tabs.map { item -> item.ordinal })
            }
        }
    }

    suspend fun setHomeTabs(tabs: List<HomeTabType>) {
        userPreferences.updateData {
            it.copy {
                homeTabs.clear()
                homeTabs.addAll(tabs.map { item -> item.ordinal })
            }
        }
    }

    suspend fun updateMainTabShopOrFriend(isShop: Boolean) {
        userPreferences.updateData {
            it.copy {
                // update mainTabs
                val mainTabsUpdateIndex =
                    mainTabs.indexOfFirst { item -> item == MainTabType.SHOP.ordinal || item == MainTabType.FRIEND.ordinal }
                if (mainTabsUpdateIndex >= 0) mainTabs[mainTabsUpdateIndex] =
                    if (isShop) MainTabType.SHOP.ordinal else MainTabType.FRIEND.ordinal
                // update homeTabs
                val homeTabsUpdateIndex =
                    homeTabs.indexOfFirst { item -> item == HomeTabType.SHOP.ordinal || item == HomeTabType.FRIEND.ordinal }
                if (homeTabsUpdateIndex >= 0) homeTabs[homeTabsUpdateIndex] =
                    if (isShop) HomeTabType.FRIEND.ordinal else HomeTabType.SHOP.ordinal
            }
        }
    }

    // 设置动态颜色
    suspend fun setDynamicColorPreference(useDynamicColor: Boolean) {
        userPreferences.updateData {
            it.copy { this.useDynamicColor = useDynamicColor }
        }
    }

    suspend fun clearUser() {
        userPreferences.updateData {
            it.copy { user = LocalUser.newBuilder().build() }
        }
    }
}