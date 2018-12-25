package com.jojo.design.common_base.utils

import android.app.Activity
import android.content.Context
import android.util.DisplayMetrics
import android.util.TypedValue
import android.view.View
import android.widget.ListView

import java.lang.reflect.Field

/**
 *    author : JOJO
 *    e-mail : 18510829974@163.com
 *    date   : 2018/12/12 10:02 PM
 *    desc   : 屏幕参数处理工具类
 */
object ScreenUtil {

    /**
     * DIP转PX
     *
     * @param context
     * @param dipValue
     * @return int
     */
    fun dip2px(context: Context, dipValue: Float): Int {
        val scale = context.resources.displayMetrics.density
        return (dipValue * scale + 0.5f).toInt()
    }

    /**
     * PX转DIP
     *
     * @param context
     * @param pxValue
     * @return int
     */
    fun px2dip(context: Context, pxValue: Float): Int {
        val scale = context.resources.displayMetrics.density
        return (pxValue / scale + 0.5f).toInt()
    }

    /**
     * float 转dp
     *
     * @param context
     * @param floatValue
     * @return
     */
    fun floatToDP(context: Context, floatValue: Float): Int {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                floatValue, context.resources.displayMetrics).toInt()
    }

    /**
     * float 转px(像素)
     *
     * @param context
     * @param floatValue
     * @return
     */
    fun floatToPX(context: Context, floatValue: Float): Int {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PX,
                floatValue, context.resources.displayMetrics).toInt()
    }

    /**
     * float 转SP
     *
     * @param context
     * @param floatValue
     * @return
     */
    fun floatToSP(context: Context, floatValue: Float): Int {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,
                floatValue, context.resources.displayMetrics).toInt()
    }


    fun getScreenWidth(context: Activity): Int {
        // 获取屏幕分辨率
        val metrics = DisplayMetrics()
        context.windowManager.defaultDisplay.getMetrics(metrics)
        return metrics.widthPixels
    }

    fun getScreenHeight(context: Activity): Int {
        // 获取屏幕分辨率
        val metrics = DisplayMetrics()
        context.windowManager.defaultDisplay.getMetrics(metrics)
        return metrics.heightPixels
    }
}

