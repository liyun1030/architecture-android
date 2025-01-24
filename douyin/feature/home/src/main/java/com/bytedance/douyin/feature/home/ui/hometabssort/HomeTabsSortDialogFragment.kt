package com.bytedance.douyin.feature.home.ui.hometabssort

import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.Window
import androidx.fragment.app.viewModels
import com.bytedance.douyin.core.architecture.app.views.AppViewsDialogFragment
import com.bytedance.douyin.core.designsystem.util.setDialogWindowBottomStyle
import com.bytedance.douyin.feature.home.R
import dagger.hilt.android.AndroidEntryPoint
import com.bytedance.douyin.feature.home.databinding.DouyinFeatureHomeFragmentHomeTabsSortBinding as ViewBinding
import com.bytedance.douyin.feature.home.ui.hometabssort.HomeTabsSortUiState as UiState
import com.bytedance.douyin.feature.home.ui.hometabssort.HomeTabsSortViewModel as ViewModel

/**
 * 描述：
 *
 * @author zhangrq
 * createTime 2024/10/23 16:32
 */
@AndroidEntryPoint
class HomeTabsSortDialogFragment : AppViewsDialogFragment<ViewBinding, UiState, ViewModel>() {
    override val viewModel: ViewModel by viewModels()
    private val adapter = HomeTabsSortAdapter()
    private val itemTouchHelper = HomeTabsSortItemTouchHelper { fromPos, toPos ->
        // 移动
        viewModel.move(fromPos, toPos)
    }
    private var outState: Bundle? = null

    override fun inflateViewBinding(inflater: LayoutInflater, container: ViewGroup?) =
        ViewBinding.inflate(inflater, container, false)

    override fun ViewBinding.initViews() {
        // recyclerView
        recyclerView.adapter = adapter
        itemTouchHelper.attachToRecyclerView(recyclerView)
    }

    override fun ViewBinding.initListeners() {
        // 关闭
        close.setOnClickListener {
            dismiss()
        }
        // Item长点击
        adapter.setOnItemLongClickListener { _, _, position ->
            if (position == 0) {
                // 第一个，不支持排序，提示。
                viewModel.showMessage(getString(R.string.douyin_feature_home_tabs_sort_not_support))
            } else {
                recyclerView.findViewHolderForAdapterPosition(position)?.let {
                    // 开始拖拽
                    itemTouchHelper.startDrag(it)
                }
            }
            return@setOnItemLongClickListener true
        }
    }

    override fun ViewBinding.initObservers() {
    }

    override fun ViewBinding.onUiStateCollect(uiState: UiState) {
        adapter.submitList(uiState.tabs)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        this.outState = outState
    }

    // ==================上面是Fragment的设置，下面是Dialog的设置==================
    override fun initDialogWindow(window: Window) {
        super.initDialogWindow(window)
        // 设置Window底部样式动画
        window.setDialogWindowBottomStyle()
    }

    override fun onDismiss(dialog: DialogInterface) {
        super.onDismiss(dialog)
        if (outState == null) {
            // 非配置变更、进程被杀死，进行保存。
            // 页面销毁，进行一次性保存操作，避免每次修改都更改本地数据。
            viewModel.save()
        }
    }
}