package com.bytedance.douyin.feature.home.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentManager.FragmentLifecycleCallbacks
import androidx.fragment.app.viewModels
import androidx.viewpager2.widget.ViewPager2
import com.bytedance.core.common.util.asSafe
import com.bytedance.core.common.util.updateMargin
import com.bytedance.douyin.core.architecture.app.views.AppViewsFragment
import com.bytedance.douyin.core.architecture.util.bindViewPager2
import com.bytedance.douyin.core.architecture.util.setDataOrAdapter
import com.bytedance.douyin.core.common.interfaces.OnFragmentBackgroundListener
import com.bytedance.douyin.core.common.interfaces.OnOpenDrawerListener
import com.bytedance.douyin.core.common.interfaces.OnSwitchTabListener
import com.bytedance.douyin.core.common.interfaces.OnTabClickRefreshFinishListener
import com.bytedance.douyin.core.common.interfaces.OnTabClickRefreshListener
import com.bytedance.douyin.core.common.interfaces.OnTabStyleListener
import com.bytedance.douyin.feature.home.R
import com.zrq.test.point.annotation.TestEntryPoint
import dagger.hilt.android.AndroidEntryPoint
import com.bytedance.douyin.feature.home.databinding.DouyinFeatureHomeFragmentHomeBinding as ViewBinding
import com.bytedance.douyin.feature.home.ui.home.HomeUiState as UiState
import com.bytedance.douyin.feature.home.ui.home.HomeViewModel as ViewModel

/**
 * 描述：
 *
 * @author zhangrq
 * createTime 2024/3/7 下午3:33
 */
@TestEntryPoint("首页")
@AndroidEntryPoint
class HomeFragment : AppViewsFragment<ViewBinding, UiState, ViewModel>(), OnTabClickRefreshListener,
    OnSwitchTabListener, OnFragmentBackgroundListener {
    override val viewModel: ViewModel by viewModels()
    override var isBackgroundBright: Boolean = true

    private val onBackPressedCallback = object : OnBackPressedCallback(false) {
        override fun handleOnBackPressed() {
            // 返回，回到最后一个。
            onSwitchTab(-1)
        }
    }
    private lateinit var navigatorAdapter: HomeNavigatorAdapter
    private val fragmentStateAdapter by lazy { HomeFragmentStateAdapter(this, requireContext()) }

    override fun inflateViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
    ) = ViewBinding.inflate(inflater, container, false)

    override fun ViewBinding.initWindowInsets() {
        // 只设置顶部Tab，其余内部Fragment处理。
        ViewCompat.setOnApplyWindowInsetsListener(magicIndicator) { v, insets ->
            val systemBars =
                insets.getInsets(WindowInsetsCompat.Type.systemBars() or WindowInsetsCompat.Type.displayCutout())
            v.updateMargin(top = systemBars.top)
            menuIv.updateMargin(start = systemBars.left + resources.getDimensionPixelSize(R.dimen.douyin_feature_home_title_margin_horizontal))
            searchIv.updateMargin(end = systemBars.right + resources.getDimensionPixelSize(R.dimen.douyin_feature_home_title_margin_horizontal))
            insets
        }
    }

    override fun ViewBinding.initViews() {
        // Indicator
        navigatorAdapter = HomeNavigatorAdapter(viewPager2, magicIndicator, childFragmentManager)
        magicIndicator.bindViewPager2(viewPager2)
        // ViewPager2
        viewPager2.setPageTransformer { _, _ -> } // 无动画
    }

    override fun ViewBinding.initListeners() {
        // 增加返回逻辑控制
        requireActivity().onBackPressedDispatcher.addCallback(
            this@HomeFragment,
            onBackPressedCallback
        )

        // 菜单
        menuIv.setOnClickListener {
//            Router.Shop.Order.startOrderActivity(requireActivity())
            activity.asSafe<OnOpenDrawerListener>()?.onOpenStartDrawer()
        }
        // 搜索
        searchIv.setOnClickListener {
        }

        // viewPager2 改变监听
        viewPager2.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                // 设置HomeTab样式，第一次为null，则会使用下面的。
                setHomeTabStyle(fragmentStateAdapter.getFragmentByIndex(position))
                // 设置此onBackPressedCallback返回逻辑是否有效
                setOnBackPressedCallbackIsEnabled()
                // 设置Tab位置改变
                viewModel.selectedTabIndexChanged(position)
            }
        })

        // 子Fragment的监听
        // 说明：
        // 1、使用此监听比onPageSelected监听，好处在于更新时，更新当前Tab为新的Fragment时也会执行。
        // 2、但是这个比上面的onPageSelected慢，配合使用以达到想要的UI效果。
        childFragmentManager.registerFragmentLifecycleCallbacks(object :
            FragmentLifecycleCallbacks() {
            override fun onFragmentResumed(fm: FragmentManager, f: Fragment) {
                super.onFragmentResumed(fm, f)
                // 设置HomeTab样式
                setHomeTabStyle(f)
            }
        }, false)
    }

    override fun ViewBinding.initObservers() {

    }

    override fun ViewBinding.onUiStateCollect(uiState: UiState) {
        // Navigator
        magicIndicator.setDataOrAdapter(uiState.tabs) { navigatorAdapter }
        // ViewPager2
        viewPager2.setDataOrAdapter(uiState.tabs, Int.MAX_VALUE) { fragmentStateAdapter }
        // 设置Tab位置
        // -说明：拖债中、设置中，用smoothScroll。空闲中，不用smoothScroll。即非空闲用smoothScroll。
        val smoothScroll = viewPager2.scrollState != ViewPager2.SCROLL_STATE_IDLE
        onSwitchTab(uiState.selectedTabIndex, smoothScroll)
    }

    override fun onResume() {
        super.onResume()
        setOnBackPressedCallbackIsEnabled()
    }

    override fun onPause() {
        super.onPause()
        setOnBackPressedCallbackIsEnabled()
    }

    // 切换tab，[index]为-1或者异常值，则切换到默认位置。
    override fun onSwitchTab(index: Int, smoothScroll: Boolean) {
        var item = index
        if (index < 0 || index >= fragmentStateAdapter.itemCount) {
            item = fragmentStateAdapter.itemCount - 1
        }
        binding?.viewPager2?.setCurrentItem(item, smoothScroll)
    }

    override fun onTabClickRefresh(listener: OnTabClickRefreshFinishListener) {
        val currentItem = binding?.viewPager2?.currentItem ?: return
        // 传给每个子TabFragment，让其进行处理。
        val currentFragment = fragmentStateAdapter.getFragmentByIndex(currentItem)
        if (currentFragment is OnTabClickRefreshListener) {
            currentFragment.onTabClickRefresh(listener)
        }
    }

    private fun setOnBackPressedCallbackIsEnabled() {
        // 当前可见，并且Tab不是最后一个，即可操作此onBackPressedCallback返回逻辑。
        onBackPressedCallback.isEnabled =
            isResumed && binding?.viewPager2?.currentItem != fragmentStateAdapter.itemCount - 1
    }

    private fun setHomeTabStyle(f: Fragment?) {
        isBackgroundBright = f.asSafe<OnFragmentBackgroundListener>()?.isBackgroundBright ?: return
        // 设置HomeTab样式
        navigatorAdapter.setIsDarkFont(isBackgroundBright)
        // 设置MainTab样式
        activity.asSafe<OnTabStyleListener>()?.onTabIsDarkFont(isBackgroundBright)
    }

    companion object {
        internal fun newInstance() = HomeFragment()
    }
}