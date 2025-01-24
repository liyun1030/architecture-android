package com.bytedance.douyin.app.main

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.activity.viewModels
import androidx.core.view.GravityCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentManager.FragmentLifecycleCallbacks
import androidx.viewpager2.widget.ViewPager2
import com.bytedance.core.common.util.asSafe
import com.bytedance.douyin.R
import com.bytedance.douyin.core.architecture.app.views.AppViewsActivity
import com.bytedance.douyin.core.architecture.util.bindViewPager2
import com.bytedance.douyin.core.architecture.util.setCommonNavigator
import com.bytedance.douyin.core.architecture.util.setDataOrAdapter
import com.bytedance.douyin.core.common.interfaces.OnFragmentBackgroundListener
import com.bytedance.douyin.core.common.interfaces.OnOpenDrawerListener
import com.bytedance.douyin.core.common.interfaces.OnSwitchTabListener
import com.bytedance.douyin.core.common.interfaces.OnTabStyleListener
import com.bytedance.douyin.core.designsystem.interfaces.empty.DefaultDrawerListener
import dagger.hilt.android.AndroidEntryPoint
import com.bytedance.douyin.app.main.MainUiState as UiState
import com.bytedance.douyin.app.main.MainViewModel as ViewModel
import com.bytedance.douyin.databinding.ActivityMainBinding as ViewBinding

/**
 * 描述：
 *
 * @author zhangrq
 * createTime 2024/3/6 上午9:02
 */
@AndroidEntryPoint
class MainActivity : AppViewsActivity<ViewBinding, UiState, ViewModel>(), OnOpenDrawerListener,
    OnTabStyleListener {
    override val viewModel: ViewModel by viewModels()

    private lateinit var navigatorAdapter: MainNavigatorAdapter

    private val fragmentStateAdapter by lazy { MainFragmentStateAdapter(this) }

    private val onBackPressedCallback = object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            // 已经控制了此回调是否有用，所以在此直接跳转即可。
            if (binding.drawerLayout.isDrawerOpen(GravityCompat.START)
                || binding.drawerLayout.isDrawerOpen(GravityCompat.END)
            ) {
                // 打开了start或者end，关闭。
                binding.drawerLayout.closeDrawers()
                return
            }
            // 首页切换到推荐
            fragmentStateAdapter.getFragmentByIndex(0).asSafe<OnSwitchTabListener>()
                ?.onSwitchTab(-1)
            // 切换到Home Tab
            binding.viewPager2.setCurrentItem(0, false)
        }
    }

    override fun inflateViewBinding(inflater: LayoutInflater) = ViewBinding.inflate(inflater)

    override fun ViewBinding.initWindowInsets() {
        // 只设置底部Tab，其余内部Fragment处理。
        ViewCompat.setOnApplyWindowInsetsListener(magicIndicator) { v, insets ->
            val systemBars =
                insets.getInsets(WindowInsetsCompat.Type.navigationBars() or WindowInsetsCompat.Type.displayCutout())
            v.setPadding(
                systemBars.left,
                0,
                systemBars.right,
                systemBars.bottom
            )
            insets
        }
    }

    override fun ViewBinding.initViews() {
        // content
        content.foreground.alpha = 0
        // Indicator
        navigatorAdapter = MainNavigatorAdapter(
            this@MainActivity, viewPager2, fragmentStateAdapter, magicIndicator
        )
        magicIndicator.setCommonNavigator { isAdjustMode = true }
        magicIndicator.bindViewPager2(viewPager2)
        // ViewPager2
        viewPager2.isUserInputEnabled = false // 不能滑动
        viewPager2.setPageTransformer { _, _ -> } // 无动画
        // drawerLayout
        // -设置颜色
        drawerLayout.setScrimColor(Color.TRANSPARENT)
        // -设置不能滑动打开
        drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
    }

    override fun ViewBinding.initListeners() {
        // 增加返回逻辑控制。handleOnBackPressed，因为兼容性，当android:enableOnBackInvokedCallback="true"没设置时（默认为false），也会生效。
        onBackPressedDispatcher.addCallback(this@MainActivity, onBackPressedCallback)
        // 拖拽滑动监听，实现拖拽效果。
        drawerLayout.addDrawerListener(object : DefaultDrawerListener {
            override fun onDrawerSlide(drawerView: View, slideOffset: Float) {
                val slideX = drawerView.width * slideOffset
                if (drawerView.id == R.id.drawer_start) {
                    content.translationX = slideX
                } else {
                    content.translationX = -slideX
                }
                content.foreground.alpha = (slideOffset * 255).toInt()
            }

            override fun onDrawerClosed(drawerView: View) {
                super.onDrawerClosed(drawerView)
                setOnBackPressedCallbackIsEnabled()
            }

            override fun onDrawerOpened(drawerView: View) {
                super.onDrawerOpened(drawerView)
                setOnBackPressedCallbackIsEnabled()
            }
        })
        // viewPager2 改变监听
        viewPager2.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                // 设置此onBackPressedCallback返回逻辑是否有效
                setOnBackPressedCallbackIsEnabled()
            }
        })
        // 子Fragment的监听
        // 说明：使用此监听比onPageSelected监听，好处在于更新时，更新当前Tab为新的Fragment时也会执行。
        supportFragmentManager.registerFragmentLifecycleCallbacks(object :
            FragmentLifecycleCallbacks() {
            override fun onFragmentResumed(fm: FragmentManager, f: Fragment) {
                super.onFragmentResumed(fm, f)
                val isBackgroundBright =
                    f.asSafe<OnFragmentBackgroundListener>()?.isBackgroundBright
                        ?: return // 没有则直接返回，防止非此Tab的Fragment。
                // 设置底部Tab当前样式
                onTabIsDarkFont(isBackgroundBright)
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
        // 不用因配置变更等（如屏幕旋转）导致的页面重新创建而记录底部Tab位置，因为ViewPager记录了，ViewPager又和底部Tab布局（MagicIndicator）关联绑绑定了。
    }

    override fun onOpenStartDrawer() {
        binding.drawerLayout.openDrawer(GravityCompat.START)
    }

    override fun onOpenEndDrawer() {
        binding.drawerLayout.openDrawer(GravityCompat.END)
    }

    // 首页顶部Tab切换样式，主页的底部Tab也切换样式。
    override fun onTabIsDarkFont(isDarkFont: Boolean) {
        navigatorAdapter.setIsDarkFont(isDarkFont)
    }

    private fun setOnBackPressedCallbackIsEnabled() {
        // drawerLayout打开，或者Tab不是第一个，即可操作此onBackPressedCallback返回逻辑。
        onBackPressedCallback.isEnabled =
            binding.drawerLayout.isDrawerOpen(GravityCompat.START)
                    || binding.drawerLayout.isDrawerOpen(GravityCompat.END)
                    || binding.viewPager2.currentItem != 0
    }
}