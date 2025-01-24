package com.bytedance.douyin.shop.feature.shop.ui.adapter


import android.content.Context
import android.view.MotionEvent
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.SimpleOnItemTouchListener
import com.bytedance.douyin.core.architecture.app.views.adapter.AppAdapter
import com.bytedance.douyin.core.architecture.app.views.adapter.AppViewBindingViewHolder
import com.bytedance.douyin.core.architecture.util.setDataOrAdapter
import com.bytedance.douyin.shop.feature.shop.R
import com.bytedance.douyin.shop.feature.shop.ui.ShopButtonUiState
import com.bytedance.douyin.shop.feature.shop.databinding.DouyinShopFeatureShopItemShopButtonsBinding as ViewBinding
import com.bytedance.douyin.shop.feature.shop.databinding.DouyinShopFeatureShopItemShopButtonsItemBinding as ItemViewBinding
import com.bytedance.douyin.shop.feature.shop.ui.ShopItemUiState as Item
import com.bytedance.douyin.shop.feature.shop.ui.adapter.ShopButtonsItemViewHolder as ItemViewHolder

/**
 * 描述：顶部按钮列表
 *
 * @author zhangrq
 * createTime 2024/11/21 14:00
 */
class ShopButtonsViewHolder(parent: ViewGroup, private val fragment: Fragment) :
    AppViewBindingViewHolder<ViewBinding, Item>(parent, ViewBinding::inflate) {

    init {
        // 解决内部RecyclerView滑动不流畅问题
        binding.buttonsRecyclerView.addOnItemTouchListener(object : SimpleOnItemTouchListener() {
            override fun onInterceptTouchEvent(rv: RecyclerView, e: MotionEvent): Boolean {
                if (e.action == MotionEvent.ACTION_MOVE) {
                    rv.parent.requestDisallowInterceptTouchEvent(true) // 禁止父布局的触摸事件
                }
                return super.onInterceptTouchEvent(rv, e)
            }
        })
    }

    override fun ViewBinding.bind(position: Int, item: Item) {
        // 设置值
        buttonsRecyclerView.setDataOrAdapter(item.buttons) { ShopButtonsItemAdapter(fragment) }
    }
}

/**
 * 顶部按钮列表-Item
 */
class ShopButtonsItemAdapter(private val fragment: Fragment) :
    AppAdapter<ItemViewBinding, ShopButtonUiState, ItemViewHolder>() {

    override fun onCreateViewHolder(context: Context, parent: ViewGroup, viewType: Int) =
        ItemViewHolder(parent, fragment)
}

class ShopButtonsItemViewHolder(parent: ViewGroup, private val fragment: Fragment) :
    AppViewBindingViewHolder<ItemViewBinding, ShopButtonUiState>(parent, ItemViewBinding::inflate) {

    override fun ItemViewBinding.bind(position: Int, item: ShopButtonUiState) {
        val shopButton = item.shopButton
        // 设置点击
        itemView.setOnClickListener {
            when (shopButton.id % 3) {
                0 -> item.addItem()
                1 -> item.deleteItem()
                else -> item.updateItem()
            }
        }
        // 设置值
//        buttonIcon.loadUrl(fragment, shopButton.image)
        buttonIcon.setImageResource(R.drawable.douyin_shop_feature_shop_ic_order)
        buttonTitle.text = shopButton.title
    }
}