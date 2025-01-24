package com.bytedance.douyin.core.data.repository.refreshloadmore.util

import com.bytedance.douyin.core.data.repository.refreshloadmore.BaseMemoryRefreshLoadMoreRepository
import kotlinx.coroutines.flow.update

/**
 * 描述：
 *
 * @author zhangrq
 * createTime 2024/9/14 下午2:34
 */
fun <Value : Any> BaseMemoryRefreshLoadMoreRepository<*, Value>.addByItem(
    predicate: (Value) -> Boolean,
    value: Value,
) {
    editItem(predicate) { tempList, index ->
        tempList.add(index, value)
    }
}

fun <Value : Any> BaseMemoryRefreshLoadMoreRepository<*, Value>.deleteByItem(
    predicate: (Value) -> Boolean,
) {
    editItem(predicate) { tempList, index ->
        tempList.removeAt(index)
    }
}

fun <Value : Any> BaseMemoryRefreshLoadMoreRepository<*, Value>.updateByItem(
    predicate: (Value) -> Boolean,
    update: Value.() -> Value,
) {
    editItem(predicate) { tempList, index ->
        val item = tempList[index]
        tempList[index] = update(item)
    }
}

private fun <Value : Any> BaseMemoryRefreshLoadMoreRepository<*, Value>.editItem(
    predicate: (Value) -> Boolean,
    action: (MutableList<Value>, Int) -> Unit,
) {
    tempListFlow.update {
        val tempList = it?.toMutableList() ?: return // 原来无数据，不操作，直接返回。
        val index = tempList.indexOfFirst(predicate)
        if (index == -1) return // 没找到，不操作，直接返回。
        action(tempList, index)
        tempList
    }
}