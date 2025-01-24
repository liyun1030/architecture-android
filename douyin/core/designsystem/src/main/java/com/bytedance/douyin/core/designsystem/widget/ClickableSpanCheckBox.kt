package com.bytedance.douyin.core.designsystem.widget

import android.annotation.SuppressLint
import android.content.Context
import android.text.Spannable
import android.text.style.ClickableSpan
import android.util.AttributeSet
import android.view.MotionEvent
import androidx.appcompat.widget.AppCompatCheckBox

/**
 * 描述：
 *
 * @author zhangrq
 * createTime 2024/5/27 下午3:19
 */
class ClickableSpanCheckBox : AppCompatCheckBox {
    constructor(context: Context?) : super(context!!)

    constructor(context: Context?, attrs: AttributeSet?) : super(
        context!!, attrs
    )

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context!!, attrs, defStyleAttr
    )

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent): Boolean {
        val text = text
        if (text is Spannable) {
            val action = event.action
            if (action == MotionEvent.ACTION_UP || action == MotionEvent.ACTION_DOWN) {
                var x = event.x.toInt()
                var y = event.y.toInt()

                x -= totalPaddingLeft
                y -= totalPaddingTop

                x += scrollX
                y += scrollY

                val layout = layout
                val line = layout.getLineForVertical(y)
                val off = layout.getOffsetForHorizontal(line, x.toFloat())

                val link = text.getSpans(off, off, ClickableSpan::class.java)
                if (link.isNotEmpty()) {
                    if (action == MotionEvent.ACTION_UP) {
                        link[0].onClick(this)
                    }
                    return true
                }
            }
        }
        return super.onTouchEvent(event)
    }
}