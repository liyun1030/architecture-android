package com.bytedance.douyin.core.network.image

import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.bumptech.glide.load.MultiTransformation
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners


/**
 * 描述：
 *
 * @author zhangrq
 * createTime 2024/3/12 上午9:40
 */
fun ImageView.loadUrl(
    activity: FragmentActivity,
    url: Any?,
    roundingRadius: Int? = null,
) = loadUrlInternal(Glide.with(activity), url, roundingRadius)

fun ImageView.loadUrl(
    fragment: Fragment,
    url: Any?,
    roundingRadius: Int? = null,
) = loadUrlInternal(Glide.with(fragment), url, roundingRadius)

/**
 * [roundingRadius]，圆角半径。
 */
private fun ImageView.loadUrlInternal(
    requestManager: RequestManager,
    url: Any?,
    roundingRadius: Int? = null,
) {
    val transformationList = mutableListOf<BitmapTransformation>(CenterCrop()) // 默认裁剪中心
    if (roundingRadius != null) transformationList.add(RoundedCorners(roundingRadius)) // 圆角矩形

    requestManager.load(url).transform(MultiTransformation(transformationList)).into(this)
}