package com.bytedance.douyin.shop.feature.shop.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.bytedance.core.common.util.dp
import com.bytedance.core.common.util.getSpanCountByAvailableWidth
import com.bytedance.douyin.core.architecture.app.views.AppViewsFragment
import com.bytedance.douyin.core.architecture.refreshloadmore.SmartRefreshLoadMoreHelper
import com.bytedance.douyin.core.common.interfaces.OnFragmentBackgroundListener
import com.bytedance.douyin.core.common.interfaces.OnTabClickRefreshFinishListener
import com.bytedance.douyin.core.common.interfaces.OnTabClickRefreshListener
import com.bytedance.douyin.core.common.util.getFragmentTopExtraHeight
import com.bytedance.douyin.core.common.util.setStatusBarDarkFont
import com.bytedance.douyin.shop.feature.shop.ui.adapter.ShopAdapter
import com.zrq.test.point.annotation.TestEntryPoint
import dagger.hilt.android.AndroidEntryPoint
import com.bytedance.douyin.shop.feature.shop.databinding.DouyinShopFeatureShopFragmentShopBinding as ViewBinding
import com.bytedance.douyin.shop.feature.shop.ui.ShopUiState as UiState
import com.bytedance.douyin.shop.feature.shop.ui.ShopViewModel as ViewModel


/**
 * 描述：
 *
 * @author zhangrq
 * createTime 2024/3/7 下午3:33
 */
@TestEntryPoint("商城")
@AndroidEntryPoint
class ShopFragment : AppViewsFragment<ViewBinding, UiState, ViewModel>(),
    OnFragmentBackgroundListener, OnTabClickRefreshListener {
    override val viewModel: ViewModel by viewModels()
    override var isBackgroundBright: Boolean = true

    private val shopAdapter = ShopAdapter(this)

    override fun getStateViewReplaceView() = binding?.smartRefreshLayout!!

    override fun inflateViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
    ) = ViewBinding.inflate(inflater, container, false)

    override fun ViewBinding.initWindowInsets() {
        // 设置页面内容Padding，以支持在可见范围显示。
        ViewCompat.setOnApplyWindowInsetsListener(root) { v, insets ->
            val systemBars =
                insets.getInsets(WindowInsetsCompat.Type.systemBars() or WindowInsetsCompat.Type.displayCutout())
            v.setPadding(
                systemBars.left,
                systemBars.top + getFragmentTopExtraHeight(),
                systemBars.right,
                0
            )
            insets
        }
    }

    override fun ViewBinding.initViews() {
        // 设置LayoutManager
        // 说明：此设置要优先于下面的设置，因为shopAdapter会设置stateView，如果没有layoutManager则会报错。
        shopGoodsList.layoutManager = StaggeredGridLayoutManager(
            // 根据可用宽，获取可用Span的数量。
            // 说明：由于spanCount是动态变化的，因此会导致列表顶部会交换或对齐等。
            getSpanCountByAvailableWidth(requireActivity(), minWidth = 180.dp),
            StaggeredGridLayoutManager.VERTICAL
        )
        // 初始化刷新加载
        SmartRefreshLoadMoreHelper(
            viewModel, shopGoodsList, shopAdapter
        ).init(
            lifecycleOwner = viewLifecycleOwner,
            refreshLayout = smartRefreshLayout,
            enableLoadMore = true
        )
    }

    override fun ViewBinding.initListeners() {

    }

    override fun ViewBinding.initObservers() {
    }

    override fun ViewBinding.onUiStateCollect(uiState: UiState) {
        shopAdapter.submitList(uiState.items)
    }

    override fun onResume() {
        super.onResume()
        setStatusBarDarkFont(isDarkFont = isBackgroundBright)
    }

    override fun onTabClickRefresh(listener: OnTabClickRefreshFinishListener) {
        binding?.shopGoodsList?.scrollToPosition(0)
        viewModel.refresh {
            listener.onTabClickRefreshFinish()
        }
    }

    companion object {
        internal fun newInstance() = ShopFragment()
    }
}