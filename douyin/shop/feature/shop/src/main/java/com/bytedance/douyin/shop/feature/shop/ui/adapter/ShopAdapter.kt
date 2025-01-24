package com.bytedance.douyin.shop.feature.shop.ui.adapter

import android.content.Context
import android.view.ViewGroup
import android.view.ViewGroup.MarginLayoutParams
import androidx.core.view.setMargins
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.bytedance.core.common.util.dp
import com.bytedance.douyin.core.architecture.app.views.adapter.AppAdapter
import com.bytedance.douyin.core.architecture.app.views.adapter.AppViewBindingViewHolder
import com.bytedance.douyin.shop.feature.shop.ui.ShopItemUiState

/**
 * 描述：
 *
 * @author zhangrq
 * createTime 2024/9/6 上午11:27
 */
class ShopAdapter(private val fragment: Fragment) :
    AppAdapter<ViewBinding, ShopItemUiState, AppViewBindingViewHolder<ViewBinding, ShopItemUiState>>() {

    @Suppress("UNCHECKED_CAST")
    override fun onCreateViewHolder(
        context: Context,
        parent: ViewGroup,
        viewType: Int,
    ): AppViewBindingViewHolder<ViewBinding, ShopItemUiState> {
        // 创建 ViewHolder
        return when (viewType) {
            1 -> ShopButtonsViewHolder(parent, fragment)
            2 -> ShopGoodsViewHolder(parent, fragment)
            3 -> ShopActivityViewHolder(parent, fragment)
            else -> ShopGoodsViewHolder(parent, fragment)
        } as AppViewBindingViewHolder<ViewBinding, ShopItemUiState>
    }

    override fun onBindViewHolder(
        holder: AppViewBindingViewHolder<ViewBinding, ShopItemUiState>,
        position: Int,
        item: ShopItemUiState?,
    ) {
        super.onBindViewHolder(holder, position, item)
        // 设置间距，此处实现也可替换为RecyclerView增加ItemDecoration。
        (holder.itemView.layoutParams as MarginLayoutParams).apply {
            setMargins(4.dp)
        }
    }

    override fun getItemViewType(position: Int, list: List<ShopItemUiState>): Int {
        return getItem(position)?.type ?: -1
    }

    override fun isFullSpanItem(itemType: Int): Boolean {
        return super.isFullSpanItem(itemType) || itemType == 1
    }
}