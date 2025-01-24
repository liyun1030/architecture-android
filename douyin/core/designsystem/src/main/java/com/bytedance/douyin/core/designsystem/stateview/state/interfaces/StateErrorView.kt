package com.bytedance.douyin.core.designsystem.stateview.state.interfaces

import android.view.View

/**
 * 描述：
 *
 * @author zhangrq
 * createTime 2024/12/11 11:51
 */
interface StateErrorView : StateBaseView {
    fun onError(error: Throwable, retry: View.OnClickListener?)
}