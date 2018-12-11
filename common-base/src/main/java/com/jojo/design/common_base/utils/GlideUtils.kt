package com.jojo.design.common_base.utils

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.Priority
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.jojo.design.common_base.BaseAppliction
import com.jojo.design.common_base.net.API

/**
 *    author : JOJO
 *    e-mail : 18510829974@163.com
 *    date   : 2018/12/10 5:30 PM
 *    desc   :
 */
object GlideUtils {
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
}