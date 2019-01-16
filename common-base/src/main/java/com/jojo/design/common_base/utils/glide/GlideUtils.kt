package com.jojo.design.common_base.utils.glide

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.Priority
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.jojo.design.common_base.BaseAppliction
import com.jojo.design.common_base.R
import com.jojo.design.common_base.utils.glide.transform.CornerOriginSizeTransform

/**
 *    author : JOJO
 *    e-mail : 18510829974@163.com
 *    date   : 2018/12/10 5:30 PM
 *    desc   :
 */
object GlideUtils {
    /**
     * 公共的加载网络图，直接加载图片地址
     *
     * @param imageUrl   图片地址
     * @param defaultImg 加载异常图
     * @param targetView 目标ImageView
     */
    fun loadNormalImage(imageUrl: String, targetView: ImageView, defaultImg: Int) {
        val requestOptions = RequestOptions()
                .placeholder(defaultImg)
                .error(defaultImg)
                .priority(Priority.HIGH)
        Glide.with(BaseAppliction.context)
                .load(imageUrl)
                .apply(requestOptions)
                .transition(DrawableTransitionOptions().crossFade())
                .into(targetView)
    }

    /**
     * 公共的加载网络图片的配置
     *
     * @param imageUrl   图片地址
     * @param defaultImg 加载异常图
     * @param targetView 目标ImageView
     */
    fun loadImage(imageUrl: String, targetView: ImageView, defaultImg: Int) {
        var image = imageUrl.substring(0, imageUrl.length - 1)
        val requestOptions = RequestOptions()
                .placeholder(defaultImg)
                .error(defaultImg)
                .priority(Priority.HIGH)
        Glide.with(BaseAppliction.context)
                .load(image + ".png")
                .apply(requestOptions)
                .transition(DrawableTransitionOptions().crossFade())
                .into(targetView)
    }

    /**
     * 按照图片原尺寸显示图片，用于当图片尺寸大小不一致，但是要求图片显示正常且全的情况
     *
     * @param imageUrl   图片地址
     * @param defaultImg 加载异常图
     * @param targetView 目标ImageView
     */
    fun loadOriginalSizeImage(imageUrl: String, targetView: ImageView, defaultImg: Int) {
        val transformation = CornerOriginSizeTransform(BaseAppliction.context, 10f)
        //上方显示圆角，下发显示直角
        transformation.setExceptCorner(false, false, true, true)
        val requestOptions = RequestOptions()
                .error(defaultImg)
                .priority(Priority.HIGH)
                .transform(transformation)//滑动过程中，会有加载过程的动画，去除这个transformation，就不会有，或者去除transition(new DrawableTransitionOptions().crossFade())也可以
        Glide.with(BaseAppliction.context)
                .load(imageUrl)
                .apply(requestOptions)
                .into(targetView)
    }

    /**
     * 圆形图片
     *
     * @param imageUrl   图片地址
     * @param defaultImg 加载异常图
     * @param targetView 目标ImageView
     */
    fun loadCircleImage(imageUrl: String, targetView: ImageView, defaultImg: Int) {
        var image = imageUrl.substring(0, imageUrl.length - 1)
        val requestOptions = RequestOptions()
                .placeholder(defaultImg)
                .error(defaultImg)
                .priority(Priority.HIGH)
                .transform(CircleCrop())
        Glide.with(BaseAppliction.context)
                .load(image + ".png")
                .apply(requestOptions)
                .transition(DrawableTransitionOptions().crossFade())
                .into(targetView)
    }
}