package com.bytedance.douyin.core.data.repository.refreshloadmore

/**
 * 描述：
 *
 * @author zhangrq
 * createTime 2024/11/13 12:03
 */
abstract class PageKeyedMemoryRefreshLoadMoreRepository<Value : Any> :
    BaseMemoryRefreshLoadMoreRepository<Int, Value>(initKey = 1) {

    override val pageSize = 20

    override fun getNextKey() = getCurrentKey() + 1
}