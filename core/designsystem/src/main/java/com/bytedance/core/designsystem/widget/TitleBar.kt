package com.bytedance.core.designsystem.widget

import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import android.util.TypedValue
import android.view.Gravity
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.core.content.res.ResourcesCompat
import com.bytedance.core.common.util.activityContext
import com.bytedance.core.common.util.dp
import com.bytedance.core.common.util.sp
import com.bytedance.core.designsystem.R

/**
 * 描述：标题栏，左右两边图片和文本支持2选1，复杂效果自己自定义。
 *
 * @author zhangrq
 * createTime 2024/4/10 下午2:19
 */
@Suppress("LeakingThis", "MemberVisibilityCanBePrivate", "unused", "SameParameterValue")
open class TitleBar @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0, defStyleRes: Int = 0,
) : FrameLayout(context, attrs, defStyleAttr, defStyleRes) {
    private var leftView: TextView? = null
    private var titleView: TextView? = null
    private var rightView: TextView? = null

    // 默认值
    // -leftText
    var defaultLeftTextSize = 15.sp
    var defaultLeftTextColorRes: Int? = null
    var defaultLeftTextTypeface = 0

    // -leftIcon
    var defaultLeftIconRes: Int? = null
    var defaultLeftIconSize: Int? = null // width/height > size > drawable.width/height
    var defaultLeftIconWidth: Int? = null
    var defaultLeftIconHeight: Int? = null

    // -title
    var defaultTitleTextSize = 20.sp
    var defaultTitleTextColorRes: Int? = null
    var defaultTitleTextTypeface = 1

    // -rightText
    var defaultRightTextSize = 15.sp
    var defaultRightTextColorRes: Int? = null
    var defaultRightTextTypeface = 0

    // -rightIcon
    var defaultRightIconRes: Int? = null
    var defaultRightIconSize: Int? = null // width/height > size > drawable.width/height
    var defaultRightIconWidth: Int? = null
    var defaultRightIconHeight: Int? = null

    // -padding
    var defaultPaddingLeft = 15.dp
    var defaultPaddingRight = 15.dp

    // -background
    var defaultBackgroundRes: Int? = null

    // -height
    var defaultHeight: Int = getActionBarHeight()

    init {
        // init default value
        initDefaultAttrValue()

        // padding
        val paddingLeft = if (paddingLeft == 0) defaultPaddingLeft else paddingLeft
        val paddingRight = if (paddingRight == 0) defaultPaddingRight else paddingRight
        setPadding(paddingLeft, 0, paddingRight, 0)
        // background
        if (background == null && defaultBackgroundRes != null) {
            setBackgroundResource(defaultBackgroundRes!!)
        }
        // height
        post {
            if (layoutParams.height == ViewGroup.LayoutParams.WRAP_CONTENT) {
                // 设置默认值
                minimumHeight = defaultHeight
            }
        }

        val array =
            context.obtainStyledAttributes(attrs, R.styleable.TitleBar, defStyleAttr, defStyleRes)
        // left view
        if (array.getBoolean(
                R.styleable.TitleBar_leftVisible,
                true
            ) && (array.hasValue(R.styleable.TitleBar_leftText) || array.hasValue(R.styleable.TitleBar_leftIcon) || defaultLeftIconRes != null)
        ) {
            // set left view
            leftView = TextView(context).apply {
                // text
                if (array.hasValue(R.styleable.TitleBar_leftText)) {
                    // text
                    text = array.getText(R.styleable.TitleBar_leftText)
                    // textSize
                    setTextSizeByAttr(
                        array, R.styleable.TitleBar_leftTextSize, defaultLeftTextSize
                    )
                    // textColor
                    setTextColorByAttr(
                        array, R.styleable.TitleBar_leftTextColor, defaultLeftTextColorRes
                    )
                    // typeface
                    setTypefaceByAttr(
                        array, R.styleable.TitleBar_leftTextStyleFlag, defaultLeftTextTypeface
                    )
                } else {
                    // image
                    setIconByAttr(
                        array, true,
                        R.styleable.TitleBar_leftIcon, defaultLeftIconRes,
                        R.styleable.TitleBar_leftIconSize, defaultLeftIconSize,
                        R.styleable.TitleBar_leftIconWidth, defaultLeftIconWidth,
                        R.styleable.TitleBar_leftIconHeight, defaultLeftIconHeight,
                    )
                }
                // gravity
                gravity = Gravity.CENTER
                // click
                setOnClickListener {
                    val activityContext = activityContext()
                    if (activityContext is ComponentActivity) {
                        activityContext.onBackPressedDispatcher.onBackPressed()
                    }
                }
            }
            // add left view
            addView(leftView,
                LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT).apply {
                    gravity = Gravity.CENTER_VERTICAL
                })
        }

        // title view
        if (array.hasValue(R.styleable.TitleBar_title)) {
            // set title view
            titleView = TextView(context).apply {
                // text
                text = array.getText(R.styleable.TitleBar_title)
                // textSize
                setTextSizeByAttr(
                    array, R.styleable.TitleBar_titleTextSize, defaultTitleTextSize
                )
                // textColor
                setTextColorByAttr(
                    array, R.styleable.TitleBar_titleTextColor, defaultTitleTextColorRes
                )
                // typeface
                setTypefaceByAttr(
                    array, R.styleable.TitleBar_titleTextStyleFlag, defaultTitleTextTypeface
                )
            }
            // add title view
            addView(titleView,
                LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT).apply {
                    gravity = Gravity.CENTER
                })
        }

        // right view
        if (array.hasValue(R.styleable.TitleBar_rightText) || array.hasValue(R.styleable.TitleBar_rightIcon) || defaultRightIconRes != null) {
            // set right view
            rightView = TextView(context).apply {
                // text
                if (array.hasValue(R.styleable.TitleBar_rightText)) {
                    // text
                    text = array.getText(R.styleable.TitleBar_rightText)
                    // textSize
                    setTextSizeByAttr(
                        array, R.styleable.TitleBar_rightTextSize, defaultRightTextSize
                    )
                    // textColor
                    setTextColorByAttr(
                        array, R.styleable.TitleBar_rightTextColor, defaultRightTextColorRes
                    )
                    // typeface
                    setTypefaceByAttr(
                        array, R.styleable.TitleBar_rightTextStyleFlag, defaultRightTextTypeface
                    )
                } else {
                    // image
                    setIconByAttr(
                        array, true,
                        R.styleable.TitleBar_rightIcon, defaultRightIconRes,
                        R.styleable.TitleBar_rightIconSize, defaultRightIconSize,
                        R.styleable.TitleBar_rightIconWidth, defaultRightIconWidth,
                        R.styleable.TitleBar_rightIconHeight, defaultRightIconHeight,
                    )
                }
                // gravity
                gravity = Gravity.CENTER
            }
            // add right view
            addView(rightView,
                LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT).apply {
                    gravity = Gravity.CENTER_VERTICAL or Gravity.END
                })
        }

        // 释放资源
        array.recycle()
    }

    open fun initDefaultAttrValue() {

    }

    private fun TextView.setTextSizeByAttr(
        array: TypedArray,
        textSizeStyleableRes: Int,
        defaultTextSize: Int,
    ) {
        setTextSize(
            TypedValue.COMPLEX_UNIT_PX,
            array.getDimensionPixelSize(textSizeStyleableRes, defaultTextSize).toFloat()
        )
    }

    private fun TextView.setTextColorByAttr(
        array: TypedArray,
        textColorStyleableRes: Int,
        defaultTextColor: Int?,
    ) {
        if (array.hasValue(textColorStyleableRes)) {
            // 有值，设置。
            setTextColor(array.getColorStateList(textColorStyleableRes))
        } else if (defaultTextColor != null) {
            setTextColor(resources.getColor(defaultTextColor, context.theme))
        }
    }

    private fun TextView.setTypefaceByAttr(
        array: TypedArray,
        typefaceStyleableRes: Int,
        defaultTextTypeface: Int,
    ) {
        setTypeface(null, array.getInt(typefaceStyleableRes, defaultTextTypeface))
    }

    private fun TextView.setIconByAttr(
        array: TypedArray,
        isLeft: Boolean,
        // icon
        iconStyleableRes: Int,
        defaultIconRes: Int?,
        // icon size
        iconSizeStyleableRes: Int,
        defaultIconSize: Int?,
        // icon width
        iconWidthStyleableRes: Int,
        defaultIconWidth: Int?,
        // icon height
        iconHeightStyleableRes: Int,
        defaultIconHeight: Int?,
    ) {
        val drawable = if (array.hasValue(iconStyleableRes)) {
            // 有值，设置。
            array.getDrawable(iconStyleableRes)
        } else if (defaultIconRes != null) {
            // 没有值，用默认的。
            ResourcesCompat.getDrawable(resources, defaultIconRes, context.theme)
        } else null
        if (drawable == null) return // 无设置，无默认，则不设置，直接返回。

        val width = getDimensionPixelSize(array, iconWidthStyleableRes, defaultIconWidth)
            ?: getDimensionPixelSize(array, iconSizeStyleableRes, defaultIconSize)
            ?: drawable.intrinsicWidth

        val height = getDimensionPixelSize(array, iconHeightStyleableRes, defaultIconHeight)
            ?: getDimensionPixelSize(array, iconSizeStyleableRes, defaultIconSize)
            ?: drawable.intrinsicHeight

        drawable.setBounds(0, 0, width, height)
        if (isLeft) setCompoundDrawables(drawable, null, null, null)
        else setCompoundDrawables(null, null, drawable, null)
    }

    private fun getDimensionPixelSize(
        array: TypedArray,
        iconWidthStyleableRes: Int,
        defaultIconWidth: Int?,
    ) = if (array.hasValue(iconWidthStyleableRes)) {
        array.getDimensionPixelSize(iconWidthStyleableRes, 0)
    } else {
        defaultIconWidth
    }

    private fun getActionBarHeight() = TypedValue().let {
        if (context.theme.resolveAttribute(
                android.R.attr.actionBarSize, it, true
            )
        ) TypedValue.complexToDimensionPixelSize(it.data, resources.displayMetrics)
        else 0
    }

    // click
    fun setLeftClick(onClickListener: OnClickListener) = apply {
        leftView?.setOnClickListener(onClickListener)
    }

    fun setTitleClick(onClickListener: OnClickListener) = apply {
        titleView?.setOnClickListener(onClickListener)
    }

    fun setRightClick(onClickListener: OnClickListener) = apply {
        rightView?.setOnClickListener(onClickListener)
    }

    // left text
    fun setLeftText(text: CharSequence) = apply {
        leftView?.text = text
    }

    fun setLeftTextSize(size: Float) = apply {
        leftView?.textSize = size
    }

    fun setLeftTextColor(color: Int) = apply {
        leftView?.setTextColor(color)
    }

    fun setLeftTextTypeface(style: Int) = apply {
        leftView?.setTypeface(null, style)
    }

    // left Icon
    fun setLeftIcon(iconRes: Int, width: Int? = null, height: Int? = null) = apply {
        val drawable = getDrawable(iconRes, width, height)
        leftView?.setCompoundDrawables(drawable, null, null, null)
    }

    // title
    fun setTitle(text: CharSequence) = apply {
        titleView?.text = text
    }

    fun setTitleTextSize(size: Float) = apply {
        titleView?.textSize = size
    }

    fun setTitleTextColor(color: Int) = apply {
        titleView?.setTextColor(color)
    }

    fun setTitleTextTypeface(style: Int) = apply {
        titleView?.setTypeface(null, style)
    }

    // right text
    fun setRightText(text: CharSequence) = apply {
        rightView?.text = text
    }

    fun setRightTextSize(size: Float) = apply {
        rightView?.textSize = size
    }

    fun setRightTextColor(color: Int) = apply {
        rightView?.setTextColor(color)
    }

    fun setRightTextTypeface(style: Int) = apply {
        rightView?.setTypeface(null, style)
    }

    // right Icon
    fun setRightIcon(iconRes: Int, width: Int? = null, height: Int? = null) = apply {
        val drawable = getDrawable(iconRes, width, height)
        rightView?.setCompoundDrawables(null, null, drawable, null)
    }

    // height
    fun setHeight(height: Int) = apply {
        post {
            layoutParams.height = height
            requestLayout()
        }
    }

    private fun getDrawable(iconRes: Int, width: Int?, height: Int?) =
        ResourcesCompat.getDrawable(resources, iconRes, context.theme)?.apply {
            setBounds(0, 0, width ?: intrinsicWidth, height ?: intrinsicHeight)
        }
}