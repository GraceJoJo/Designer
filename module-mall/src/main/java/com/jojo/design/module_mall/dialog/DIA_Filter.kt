package com.jojo.design.module_mall.dialog

import android.app.ActionBar
import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.WindowManager
import android.widget.LinearLayout
import com.jojo.design.common_base.utils.ScreenUtil
import com.jojo.design.module_mall.R

/**
 *    author : JOJO
 *    e-mail : 18510829974@163.com
 *    date   : 2019/1/9 4:57 PM
 *    desc   : 商品筛选弹窗
 */
class DIA_Filter constructor(context: Activity) {
    protected var mDialog: Dialog? = null
    var mContentView: View? = null
    protected var mContext: Activity? = null
    var cancelable = true

    init {
        mContext = context
        mDialog = Dialog(context, R.style.style_custom_dialog)
        mContentView = LayoutInflater.from(context).inflate(R.layout.dia_filter, null)
        mDialog?.setContentView(mContentView)
    }


    fun getDialog(): Dialog {
        if (mDialog?.window != null) {
            mDialog?.window!!.setGravity(Gravity.RIGHT)
            mDialog?.window!!.setWindowAnimations(R.style.AnimationRightFade)
            val lp = mDialog?.window!!.attributes
            //背景灰度
            lp.dimAmount = 0.5f
            lp.alpha = 1f
            lp.height = LinearLayout.LayoutParams.MATCH_PARENT
            lp.width = ScreenUtil.getScreenWidth(mContext!!) / 3 * 2
        }
        mDialog?.setCancelable(cancelable)
        mDialog?.setCanceledOnTouchOutside(cancelable)
        return mDialog!!
    }

    fun show() {
        getDialog().show()
    }

    fun dismiss() {
        mDialog?.dismiss()
    }
}