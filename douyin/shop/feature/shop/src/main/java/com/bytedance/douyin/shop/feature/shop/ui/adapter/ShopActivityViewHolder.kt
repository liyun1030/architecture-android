package com.bytedance.douyin.shop.feature.shop.ui.adapter

import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bytedance.douyin.core.architecture.app.views.adapter.AppViewBindingViewHolder
import com.bytedance.douyin.core.network.image.loadUrl
import com.bytedance.douyin.shop.feature.shop.R
import com.bytedance.douyin.shop.feature.shop.databinding.DouyinShopFeatureShopItemShopActivityBinding as ViewBinding
import com.bytedance.douyin.shop.feature.shop.ui.ShopItemUiState as Item

/**
 * 描述：活动
 *
 * @author zhangrq
 * createTime 2024/11/21 13:58
 */
class ShopActivityViewHolder(parent: ViewGroup, private val fragment: Fragment) :
    AppViewBindingViewHolder<ViewBinding, Item>(parent, ViewBinding::inflate) {

    override fun ViewBinding.bind(position: Int, item: Item) {
        val shop = item.shop ?: return
        // 设置点击
        itemView.setOnClickListener { item.deleteItem() }
        // 设置值
        activityBackground.loadUrl(fragment, shop.background)
        activityImage.loadUrl(
            fragment,
            shop.image,
            roundingRadius = context.resources.getDimensionPixelSize(R.dimen.douyin_shop_feature_shop_item_card_corner_radius)
        )
    }
}