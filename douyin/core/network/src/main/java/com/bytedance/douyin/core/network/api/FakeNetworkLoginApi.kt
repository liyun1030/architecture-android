package com.bytedance.douyin.core.network.api

import com.bytedance.douyin.core.network.model.FakeNetworkSentences
import com.bytedance.douyin.core.network.model.FakeNetworkUser
import com.bytedance.douyin.core.network.model.base.ApiOpenBaseNetworkModel
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

/**
 * 描述：
 *
 * @author zhangrq
 * createTime 2024/9/26 上午11:29
 */
interface FakeNetworkLoginApi {
    /**
     * 登录
     */
    @POST("api/login")
    @FormUrlEncoded
    suspend fun login(
        @Field("account") account: String,
        @Field("password") password: String,
    ): ApiOpenBaseNetworkModel<FakeNetworkUser>

    /**
     * 一言名句
     */
    @GET("api/sentences")
    suspend fun sentences(): ApiOpenBaseNetworkModel<FakeNetworkSentences>
}