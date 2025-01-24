package com.bytedance.douyin.feature.home.ui.hometabssort

import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView

/**
 * 描述：
 *
 * @author zhangrq
 * createTime 2024/10/24 16:48
 */
class HomeTabsSortItemTouchHelper(
    onMoved: (Int, Int) -> Unit,
) : ItemTouchHelper(object : Callback() {
    override fun getMovementFlags(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
    ) = makeMovementFlags(UP or DOWN, 0)

    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder,
    ) = target.layoutPosition != 0

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
    }

    override fun onMoved(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        fromPos: Int,
        target: RecyclerView.ViewHolder,
        toPos: Int,
        x: Int,
        y: Int,
    ) {
        super.onMoved(recyclerView, viewHolder, fromPos, target, toPos, x, y)
        // 通知
        onMoved(fromPos, toPos)
    }

    // 设置不支持默认长点击效果
    override fun isLongPressDragEnabled() = false
})