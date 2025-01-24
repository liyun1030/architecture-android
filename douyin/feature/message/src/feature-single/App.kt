package com.bytedance.douyin.feature

import android.app.Application
import com.zrq.test.point.annotation.TestEntryPointModules
import dagger.hilt.android.HiltAndroidApp

/**
 * 描述：
 *
 * @author zhangrq
 * createTime 2024/3/7 下午2:36
 */
@HiltAndroidApp
@TestEntryPointModules
class App : Application()