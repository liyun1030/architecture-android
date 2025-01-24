@file:Suppress("UNCHECKED_CAST")

package com.bytedance.core.architecture.reflect

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import com.bytedance.core.architecture.base.BaseViewModel
import com.bytedance.core.architecture.base.views.BaseViewsActivity
import com.bytedance.core.architecture.base.views.BaseViewsFragment
import java.lang.reflect.ParameterizedType

/**
 * 描述：反射实现ViewBinding.inflate()
 *
 * @author zhangrq
 * createTime 2024/8/19 下午4:49
 */

/**
 * Activity-reflectViewBinding
 */
fun <Binding : ViewBinding, UiState : Any, ViewModel : BaseViewModel<UiState>> BaseViewsActivity<Binding, UiState, ViewModel>.reflectInflateViewBinding(
    layoutInflater: LayoutInflater,
): Binding {
    // Java reflect
    // -get ViewBinding class
    val viewBindingClass =
        (javaClass.genericSuperclass as ParameterizedType).actualTypeArguments[0] as Class<*>
    // -viewBinding.inflate() invoke
    val viewBindingInflateInvoke = viewBindingInflateInvoke(viewBindingClass, layoutInflater, null)
    return viewBindingInflateInvoke as Binding
}

/**
 * Fragment-reflectViewBinding
 */
fun <Binding : ViewBinding, UiState : Any, ViewModel : BaseViewModel<UiState>> BaseViewsFragment<Binding, UiState, ViewModel>.reflectInflateViewBinding(
    layoutInflater: LayoutInflater, container: ViewGroup?,
): Binding {
    // Java reflect
    // -get ViewBinding class
    val viewBindingClass =
        (javaClass.genericSuperclass as ParameterizedType).actualTypeArguments[0] as Class<*>
    // -viewBinding.inflate() invoke
    return viewBindingInflateInvoke(viewBindingClass, layoutInflater, container) as Binding
}

private fun viewBindingInflateInvoke(
    viewBindingClass: Class<*>,
    layoutInflater: LayoutInflater,
    container: ViewGroup?,
): Any? {
    val method = viewBindingClass.getMethod(
        "inflate", LayoutInflater::class.java, ViewGroup::class.java, Boolean::class.java
    )
    return method.invoke(null, layoutInflater, container, false)
}