package com.bytedance.douyin.core.architecture.refreshloadmore

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.chad.library.adapter4.BaseQuickAdapter

/**
 * 描述：为了解决了DifferAdapter滚动错乱问题
 * 问题链接：https://github.com/CymChad/BaseRecyclerViewAdapterHelper/issues/3883
 * @author zhangrq
 * createTime 2024/11/13 15:08
 */
class FixBugHeaderAdapter : BaseQuickAdapter<Any, FixBugHeaderAdapter.VH>() {

    class VH(view: View) : RecyclerView.ViewHolder(view)

    override fun getItemCount(items: List<Any>) = 1

    override fun onCreateViewHolder(context: Context, parent: ViewGroup, viewType: Int) =
        // 占位用，解决因数据获取的太快，导致刷新时会自动滑到列表底部。
        VH(View(context).apply {
            // 最少有1px，否则会导致SwipeRefreshLayout，不能刷新。
            minimumHeight = 1
        })

    override fun onBindViewHolder(holder: VH, position: Int, item: Any?) {
    }

}