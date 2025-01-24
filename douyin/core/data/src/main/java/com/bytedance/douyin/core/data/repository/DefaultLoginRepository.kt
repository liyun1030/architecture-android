package com.bytedance.douyin.core.data.repository

import com.bytedance.douyin.core.data.model.asExternalModel
import com.bytedance.douyin.core.data.repository.interfaces.LoginRepository
import com.bytedance.douyin.core.datastore.AppPreferencesDataSource
import com.bytedance.douyin.core.model.User
import com.bytedance.douyin.core.network.datasource.interfaces.NetworkLoginDataSource
import com.bytedance.douyin.core.network.model.NetworkUser
import javax.inject.Inject

/**
 * 描述：
 *
 * @author zhangrq
 * createTime 2024/7/2 下午4:33
 */
class DefaultLoginRepository @Inject constructor(
    private val preferencesDataSource: AppPreferencesDataSource,
    private val networkDataSource: NetworkLoginDataSource,
) : LoginRepository {

    override suspend fun sendLoginPhoneNumberVerificationCode(phoneNumber: String) {
        networkDataSource.sendLoginPhoneNumberVerificationCode(phoneNumber)
    }

    override suspend fun loginByPhoneNumberAndVerificationCode(
        phoneNumber: String,
        verificationCode: String,
    ): User {
        return networkDataSource.loginByPhoneNumberAndVerificationCode(
            phoneNumber,
            verificationCode
        ).asUserAndSave()
    }

    override suspend fun loginByPhoneNumberAndPassword(
        phoneNumber: String,
        password: String,
    ): User {
        return networkDataSource.loginByPhoneNumberAndPassword(phoneNumber, password)
            .asUserAndSave()
    }

    override suspend fun loginByEmailAndPassword(email: String, password: String): User {
        return networkDataSource.loginByEmailAndPassword(email, password).asUserAndSave()
    }

    override suspend fun sendRetrievePasswordPhoneNumberVerificationCode(phoneNumber: String) {
        networkDataSource.sendRetrievePasswordPhoneNumberVerificationCode(phoneNumber)
    }

    override suspend fun verifyRetrievePasswordByPhoneNumberAndVerificationCode(
        phoneNumber: String,
        verificationCode: String,
    ) {
        networkDataSource.verifyRetrievePasswordByPhoneNumberAndVerificationCode(
            phoneNumber,
            verificationCode
        )
    }

    override suspend fun retrievePasswordNewPassword(
        phoneNumber: String,
        password: String,
    ): User {
        return networkDataSource.retrievePasswordNewPassword(phoneNumber, password).asUserAndSave()
    }

    private suspend fun NetworkUser.asUserAndSave() = asExternalModel().apply {
        preferencesDataSource.setUser(this)
    }
}