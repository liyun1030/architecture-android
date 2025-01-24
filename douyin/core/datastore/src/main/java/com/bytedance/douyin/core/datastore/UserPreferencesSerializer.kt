package com.bytedance.douyin.core.datastore

import androidx.datastore.core.CorruptionException
import androidx.datastore.core.Serializer
import com.google.protobuf.InvalidProtocolBufferException
import java.io.InputStream
import java.io.OutputStream
import javax.inject.Inject

/**
 * 描述：
 *
 * @author zhangrq
 * createTime 2024/3/13 上午10:53
 */
class UserPreferencesSerializer @Inject constructor() : Serializer<UserPreferences> {
    override val defaultValue: UserPreferences = UserPreferences.getDefaultInstance()

    override suspend fun readFrom(input: InputStream): UserPreferences =
        try {
            // readFrom已经在数据存储后台线程上被调用
            UserPreferences.parseFrom(input)
        } catch (exception: InvalidProtocolBufferException) {
            throw CorruptionException("Cannot read proto.", exception)
        }

    override suspend fun writeTo(t: UserPreferences, output: OutputStream) {
        // 已经在数据存储后台线程上调用了writeTo
        t.writeTo(output)
    }
}
