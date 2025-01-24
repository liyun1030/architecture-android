@file:Suppress("UNCHECKED_CAST")

package com.bytedance.core.architecture.reflect

import androidx.annotation.MainThread
import androidx.fragment.app.createViewModelLazy
import androidx.lifecycle.HasDefaultViewModelProviderFactory
import androidx.lifecycle.ViewModelLazy
import androidx.lifecycle.ViewModelProvider.Factory
import androidx.lifecycle.ViewModelStoreOwner
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.viewbinding.ViewBinding
import com.bytedance.core.architecture.base.BaseViewModel
import com.bytedance.core.architecture.base.views.BaseViewsActivity
import com.bytedance.core.architecture.base.views.BaseViewsFragment
import kotlin.reflect.KClass
import kotlin.reflect.full.allSupertypes

/**
 * 描述：反射实现ViewModel的获取
 *
 * @author zhangrq
 * createTime 2024/8/19 下午5:17
 */

/**
 * Activity-reflectViewModels
 */
@MainThread
fun <Binding : ViewBinding, UiState : Any, ViewModel : BaseViewModel<UiState>> BaseViewsActivity<Binding, UiState, ViewModel>.reflectViewModels(
    extrasProducer: (() -> CreationExtras)? = null,
    factoryProducer: (() -> Factory)? = null,
): Lazy<ViewModel> {
    // Kotlin reflect
    val kType = this::class.allSupertypes.single { it.classifier == BaseViewsActivity::class }
    val viewModelClass = kType.arguments[2].type?.classifier as KClass<ViewModel>

    val factoryPromise = factoryProducer ?: {
        defaultViewModelProviderFactory
    }
    return ViewModelLazy(
        viewModelClass,
        { viewModelStore },
        factoryPromise,
        { extrasProducer?.invoke() ?: this.defaultViewModelCreationExtras })
}

/**
 * Fragment-reflectViewModels
 */
@MainThread
fun <Binding : ViewBinding, UiState : Any, ViewModel : BaseViewModel<UiState>> BaseViewsFragment<Binding, UiState, ViewModel>.reflectViewModels(
    ownerProducer: () -> ViewModelStoreOwner = { this },
    extrasProducer: (() -> CreationExtras)? = null,
    factoryProducer: (() -> Factory)? = null,
): Lazy<ViewModel> {
    // Kotlin reflect
    val kType = this::class.allSupertypes.single { it.classifier == BaseViewsFragment::class }
    val viewModelClass = kType.arguments[2].type?.classifier as KClass<ViewModel>

    val owner by lazy(LazyThreadSafetyMode.NONE) { ownerProducer() }
    return createViewModelLazy(
        viewModelClass,
        { owner.viewModelStore },
        {
            extrasProducer?.invoke()
                ?: (owner as? HasDefaultViewModelProviderFactory)?.defaultViewModelCreationExtras
                ?: CreationExtras.Empty
        },
        factoryProducer ?: {
            (owner as? HasDefaultViewModelProviderFactory)?.defaultViewModelProviderFactory
                ?: defaultViewModelProviderFactory
        })
}

/**
 * Fragment-reflectActivityViewModels
 */
@MainThread
fun <Binding : ViewBinding, UiState : Any, ViewModel : BaseViewModel<UiState>> BaseViewsFragment<Binding, UiState, ViewModel>.reflectActivityViewModels(
    extrasProducer: (() -> CreationExtras)? = null,
    factoryProducer: (() -> Factory)? = null,
): Lazy<ViewModel> {
    // Kotlin reflect
    val kType = this::class.allSupertypes.single { it.classifier == BaseViewsFragment::class }
    val viewModelClass = kType.arguments[2].type?.classifier as KClass<ViewModel>

    return createViewModelLazy(
        viewModelClass,
        { requireActivity().viewModelStore },
        { extrasProducer?.invoke() ?: requireActivity().defaultViewModelCreationExtras },
        factoryProducer ?: { requireActivity().defaultViewModelProviderFactory }
    )
}