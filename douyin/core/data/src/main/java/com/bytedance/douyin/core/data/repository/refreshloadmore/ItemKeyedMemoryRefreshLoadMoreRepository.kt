package com.bytedance.douyin.core.data.repository.refreshloadmore

/**
 * 描述：
 *
 * @author zhangrq
 * createTime 2024/11/13 12:04
 */
abstract class ItemKeyedMemoryRefreshLoadMoreRepository<Value : Any> :
    BaseMemoryRefreshLoadMoreRepository<Value?, Value>(initKey = null) {

    override val pageSize = 20

    override fun getNextKey() = tempListFlow.value?.lastOrNull()
}