package com.bytedance.douyin.core.architecture.app.views.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import com.bytedance.core.architecture.base.views.adapter.BaseViewBindingViewHolder

/**
 * 描述：
 *
 * @author zhangrq
 * createTime 2024/10/24 15:21
 */
abstract class AppViewBindingViewHolder<Binding : ViewBinding, Item>(
    parent: ViewGroup,
    bindingCreator: (LayoutInflater, ViewGroup?, Boolean) -> Binding,
) : BaseViewBindingViewHolder<Binding, Item>(parent, bindingCreator)