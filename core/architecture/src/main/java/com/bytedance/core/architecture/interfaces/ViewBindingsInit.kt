package com.bytedance.core.architecture.interfaces

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding

/**
 * 描述：
 *
 * @author zhangrq
 * createTime 2024/2/26 下午3:34
 */
// 没封装父类，因为实现子类时，会影响方法的实现顺序（inflateViewBinding、getViewBinding方法会在其他方法后面）。
@Suppress("EmptyMethod")
interface ViewBindingActivity<Binding : ViewBinding, UiState> {
    // 设置为属性，是因为能调用此属性获取。
    val binding: Binding
    fun inflateViewBinding(inflater: LayoutInflater): Binding
    fun Binding.initWindowInsets() {}

    fun Binding.initViews()
    fun Binding.initListeners()
    fun Binding.initBaseObservers()
    fun Binding.initObservers()

    fun Binding.onUiStateCollect(uiState: UiState)
}

interface ViewBindingFragment<Binding : ViewBinding, UiState> {
    val binding: Binding?
    fun inflateViewBinding(inflater: LayoutInflater, container: ViewGroup?): Binding

    fun initOnViewCreated() {}
    fun Binding.initWindowInsets() {}

    fun Binding.initViews()
    fun Binding.initListeners()
    fun Binding.initBaseObservers()
    fun Binding.initObservers()

    fun Binding.onUiStateCollect(uiState: UiState)
}