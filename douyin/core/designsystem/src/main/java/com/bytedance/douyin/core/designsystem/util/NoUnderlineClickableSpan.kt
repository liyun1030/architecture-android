package com.bytedance.douyin.core.designsystem.util

import android.text.TextPaint
import android.text.style.ClickableSpan
import android.view.View

/**
 * 描述：
 *
 * @author zhangrq
 * createTime 2024/7/1 下午4:01
 */
class NoUnderlineClickableSpan(private val click: (() -> Unit)? = null) : ClickableSpan() {
    override fun onClick(widget: View) {
        click?.invoke()
    }

    override fun updateDrawState(ds: TextPaint) {
        super.updateDrawState(ds)
        ds.isUnderlineText = false
    }
}