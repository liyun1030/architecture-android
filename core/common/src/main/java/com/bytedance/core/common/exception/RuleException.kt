package com.bytedance.core.common.exception

/**
 * 描述：
 *
 * @author zhangrq
 * createTime 2024/11/20 17:44
 */
class RuleException(val code: Int?, message: String?) : RuntimeException(message)