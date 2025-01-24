/*
 * Copyright 2022 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.bytedance.douyin.core.network.di

import com.bytedance.douyin.core.network.BuildConfig
import com.bytedance.douyin.core.network.util.BASE_URL_API_OPEN
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.Call
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory
import javax.inject.Singleton

/**
 * 描述：
 *
 * @author zhangrq
 * createTime 2024/11/28 10:30
 */
@Module
@InstallIn(SingletonComponent::class)
// 单例，网络层提供：Json、OkHttpClient、Retrofit。
internal object NetworkModule {

    @Provides
    @Singleton
    // 单例，提供Json
    fun providesNetworkJson(): Json = Json {
        // 忽略未知键，即支持json串多返回属性。
        ignoreUnknownKeys = true
        // 宽松的解析，即支持json串的string值可以解析为Int、Long等。
        isLenient = true
    }

    @Provides
    @Singleton
    // 单例，提供OkHttpClient
    fun okHttpCallFactory(): Call.Factory =
        OkHttpClient.Builder()
            .addInterceptor(
                HttpLoggingInterceptor()
                    .apply {
                        if (BuildConfig.DEBUG) {
                            setLevel(HttpLoggingInterceptor.Level.BODY)
                        }
                    },
            )
            .build()

    @Provides
    @Singleton
    // 单例，提供Retrofit
    fun providesRetrofit(
        networkJson: Json,
        okhttpCallFactory: dagger.Lazy<Call.Factory>,
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL_API_OPEN)
            .callFactory { okhttpCallFactory.get().newCall(it) }
            .addConverterFactory(
                networkJson.asConverterFactory("application/json".toMediaType()),
            )
            .build()
    }
}