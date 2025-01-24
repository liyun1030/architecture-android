package com.bytedance.douyin.core.network.datasource.interfaces

import com.bytedance.douyin.core.network.model.NetworkUser

/**
 * 描述：
 *
 * @author zhangrq
 * createTime 2024/11/28 10:23
 */
interface NetworkLoginDataSource {

    /**
     * 登录-（手机号+验证码）-发送验证码
     */
    suspend fun sendLoginPhoneNumberVerificationCode(phoneNumber: String)

    /**
     * 登录-（手机号+验证码）
     */
    suspend fun loginByPhoneNumberAndVerificationCode(
        phoneNumber: String,
        verificationCode: String,
    ): NetworkUser

    /**
     * 登录-（手机号+密码）
     */
    suspend fun loginByPhoneNumberAndPassword(phoneNumber: String, password: String): NetworkUser

    /**
     * 登录-（邮箱+密码）
     */
    suspend fun loginByEmailAndPassword(email: String, password: String): NetworkUser

    /**
     * 找回密码-（手机号+验证码）-发送验证码
     */
    suspend fun sendRetrievePasswordPhoneNumberVerificationCode(phoneNumber: String)

    /**
     * 找回密码-（手机号+验证码）-验证
     */
    suspend fun verifyRetrievePasswordByPhoneNumberAndVerificationCode(
        phoneNumber: String,
        verificationCode: String,
    )

    /**
     * 找回密码-新密码
     */
    suspend fun retrievePasswordNewPassword(phoneNumber: String, password: String): NetworkUser
}