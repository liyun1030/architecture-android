package com.bytedance.douyin.core.datastore.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.core.DataStoreFactory
import androidx.datastore.dataStoreFile
import com.bytedance.douyin.core.common.network.AppDispatchers
import com.bytedance.douyin.core.common.network.Dispatcher
import com.bytedance.douyin.core.common.network.di.ApplicationScope
import com.bytedance.douyin.core.datastore.UserPreferences
import com.bytedance.douyin.core.datastore.UserPreferencesSerializer
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import javax.inject.Singleton

/**
 * 描述：
 *
 * @author zhangrq
 * createTime 2024/3/13 上午10:53
 */
@Module
@InstallIn(SingletonComponent::class)
// 单例，提供DataStore
object DataStoreModule {

    @Provides
    @Singleton
    internal fun providesUserPreferencesDataStore(
        // ApplicationContext
        @ApplicationContext context: Context,
        // Dispatchers.IO线程
        @Dispatcher(AppDispatchers.IO) ioDispatcher: CoroutineDispatcher,
        // 全局Scope，异常不会影响其他。
        @ApplicationScope scope: CoroutineScope,
        // UserPreferences的序列化器
        userPreferencesSerializer: UserPreferencesSerializer,
    ): DataStore<UserPreferences> =
        DataStoreFactory.create(
            serializer = userPreferencesSerializer,
            scope = CoroutineScope(scope.coroutineContext + ioDispatcher),
        ) {
            // 生产文件
            context.dataStoreFile("user_preferences.pb")
        }
}