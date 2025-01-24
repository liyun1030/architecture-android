package com.bytedance.douyin.core.architecture.util

import androidx.recyclerview.widget.RecyclerView
import com.bytedance.core.model.BaseDiffItem
import com.bytedance.core.common.util.asUnsafe
import com.bytedance.douyin.core.architecture.app.views.adapter.AppDifferAdapter

/**
 * 描述：
 *
 * @author zhangrq
 * createTime 2024/11/21 上午11:22
 */

/**
 * 设置[list]数据和[createAdapter]创建的Adapter。
 */
fun <T : BaseDiffItem> RecyclerView.setDataOrAdapter(
    list: List<T>?,
    createAdapter: () -> AppDifferAdapter<T, *>,
) {
    if (adapter == null) {
        // create adapter
        val recyclerViewAdapter = createAdapter()
        recyclerViewAdapter.submitList(list)
        // set adapter
        adapter = recyclerViewAdapter
    } else {
        // update adapter
        adapter.asUnsafe<AppDifferAdapter<T, *>>().submitList(list)
    }
}