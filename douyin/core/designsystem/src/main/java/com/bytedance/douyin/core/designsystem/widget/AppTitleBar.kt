package com.bytedance.douyin.core.designsystem.widget

import android.content.Context
import android.util.AttributeSet
import com.bytedance.core.common.util.dp
import com.bytedance.core.common.util.sp
import com.bytedance.core.designsystem.widget.TitleBar
import com.bytedance.douyin.core.designsystem.R

/**
 * 描述：
 *
 * @author zhangrq
 * createTime 2024/6/28 上午11:02
 */
class AppTitleBar @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
    defStyleRes: Int = 0,
) : TitleBar(context, attrs, defStyleAttr, defStyleRes) {

    override fun initDefaultAttrValue() {
        super.initDefaultAttrValue()
        // -leftText
        defaultLeftTextSize = 15.sp
        // -leftIcon
        defaultLeftIconRes = R.drawable.douyin_core_designsystem_ic_back

        // -title
        defaultTitleTextSize = 20.sp
        defaultTitleTextTypeface = 1

        // -rightText
        defaultRightTextSize = 15.sp
        // -rightIcon
        defaultRightIconRes = null

        // -padding
        defaultPaddingLeft = 15.dp
        defaultPaddingRight = 15.dp
    }
}