package com.bytedance.douyin.core.common.util

import com.bytedance.core.common.exception.RuleException

/**
 * 描述：网络异常相关
 *
 * @author zhangrq
 * createTime 2024/11/26 上午10:16
 */
fun Throwable.isEmptyError() = this is RuleException && code == RULE_EXCEPTION_EMPTY_CODE

const val RULE_EXCEPTION_EMPTY_CODE = 100