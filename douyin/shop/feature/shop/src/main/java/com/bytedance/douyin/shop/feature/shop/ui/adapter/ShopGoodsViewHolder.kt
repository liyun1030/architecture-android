package com.bytedance.douyin.shop.feature.shop.ui.adapter

import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bytedance.core.common.util.getDataFromThemeAttr
import com.bytedance.douyin.core.architecture.app.views.adapter.AppViewBindingViewHolder
import com.bytedance.douyin.core.network.image.loadUrl
import com.bytedance.douyin.shop.feature.shop.R
import com.zrq.spanbuilder.Spans
import com.bytedance.douyin.shop.feature.shop.databinding.DouyinShopFeatureShopItemShopGoodsBinding as ViewBinding
import com.bytedance.douyin.shop.feature.shop.ui.ShopItemUiState as Item
import com.google.android.material.R as materialR

/**
 * 描述：商品
 *
 * @author zhangrq
 * createTime 2024/11/21 11:32
 */
class ShopGoodsViewHolder(parent: ViewGroup, private val fragment: Fragment) :
    AppViewBindingViewHolder<ViewBinding, Item>(parent, ViewBinding::inflate) {

    override fun ViewBinding.bind(position: Int, item: Item) {
        val shop = item.shop ?: return
        // 设置点击
        itemView.setOnClickListener {
            if ((shop.id ?: 0) % 2 == 0) item.addItem() else item.updateItem()
        }
        // 设置值
        goodsImage.loadUrl(fragment, shop.image)
        goodsTitle.text = shop.title
        goodsPriceAndSoldNum.text = Spans.builder()
            .appendPrice(shop.price)
            .appendSoldNum(shop.soldNum)
            .build()
    }

    private fun Spans.Builder.appendPrice(price: Double?) = apply {
        price ?: return@apply // 为空，直接返回。
        text(context.getString(R.string.douyin_shop_feature_shop_currency_symbol)) // 拼接金钱符号
        val split = price.toString().split(".")
        if (split.isNotEmpty()) {
            text(split[0]).size(18) // 拼接整数
        }
        if (split.size > 1) {
            text(".${split[1]}") // 拼接小数
        }
    }

    private fun Spans.Builder.appendSoldNum(soldNum: String?) = apply {
        soldNum ?: return@apply // 为空，直接返回。
        val normalColor = context.getDataFromThemeAttr(materialR.attr.colorOnSurfaceVariant)
        text(" ${context.getString(R.string.douyin_shop_feature_shop_goods_sold, soldNum)}")
            .color(normalColor)
    }
}