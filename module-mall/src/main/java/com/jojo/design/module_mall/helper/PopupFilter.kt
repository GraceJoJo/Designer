package com.jojo.design.module_mall.helper

import android.app.Activity
import android.graphics.drawable.BitmapDrawable
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.widget.LinearLayout
import android.widget.PopupWindow
import com.jojo.design.common_base.utils.RecyclerviewHelper
import com.jojo.design.common_ui.view.MyPopupWindow
import com.jojo.design.module_mall.R

/**
 *    author : JOJO
 *    e-mail : 18510829974@163.com
 *    date   : 2019/1/2 5:52 PM
 *    desc   : 筛选列表弹窗
 */
class PopupFilter {

    companion object {
        var mPopWindow: PopupWindow? = null
        fun initPopupWindow(mContext: Activity, adapter: RecyclerView.Adapter<*>): MyPopupWindow {
            //设置contentView，在布局中外层macthParent的布局中设置半透明的背景阴影
            var contentView = LayoutInflater.from(mContext).inflate(R.layout.popup_goods_filter, null)
            var recyclerviewFilter = contentView.findViewById<RecyclerView>(R.id.rv)
            var ll_popup_root = contentView.findViewById<LinearLayout>(R.id.ll_popup_root)
            ll_popup_root.setOnClickListener { mPopWindow!!.dismiss() }
            //适配7.0版本
            mPopWindow = MyPopupWindow(contentView, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT)
//            mPopWindow = PopupWindow(this) //7.0以上,高度设置为matchParent会导致popupwindow覆盖全屏，showAsDropDown()方法无效
            //必须设置宽高才能显示出来
            mPopWindow!!.height = LinearLayout.LayoutParams.MATCH_PARENT
            mPopWindow!!.width = LinearLayout.LayoutParams.MATCH_PARENT
            mPopWindow!!.setContentView(contentView)
            mPopWindow!!.animationStyle = R.style.AnimationRightFade
            //解决5.0以下版本点击外部不消失问题
            mPopWindow!!.setOutsideTouchable(true)
            mPopWindow!!.setFocusable(true)
            mPopWindow!!.setBackgroundDrawable(BitmapDrawable())
            RecyclerviewHelper.initNormalRecyclerView(mContext, recyclerviewFilter!!, adapter!!, LinearLayoutManager(mContext))

            return mPopWindow as MyPopupWindow
        }
    }
}