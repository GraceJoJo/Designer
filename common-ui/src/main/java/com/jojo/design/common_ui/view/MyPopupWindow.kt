package com.jojo.design.common_ui.view

import android.graphics.Rect
import android.os.Build
import android.view.View
import android.widget.PopupWindow

/**
 *    author : JOJO
 *    e-mail : 18510829974@163.com
 *    date   : 2019/1/2 5:58 PM
 *    desc   : 解决在Android 7.0上PopupWindow.showAsDropDown不起作用
 */
class MyPopupWindow constructor(mMenuView: View, matchParent: Int, matchParent1: Int) : PopupWindow(mMenuView, matchParent, matchParent1) {
    //    override fun showAsDropDown(anchor: View?) {
//        if (Build.VERSION.SDK_INT >= 24) {
//            var rect = Rect()
//            anchor!!.getGlobalVisibleRect(rect);
//            var h = anchor!!.getResources().getDisplayMetrics().heightPixels - rect.bottom;
//            setHeight(h)
//        }
//        super.showAsDropDown(anchor)
//    }
    override fun showAsDropDown(anchor: View) {
        if (Build.VERSION.SDK_INT >= 24) {
            val rect = Rect()
            anchor.getGlobalVisibleRect(rect)
            val h = anchor.resources.displayMetrics.heightPixels - rect.bottom
            height = h
        }
        super.showAsDropDown(anchor)
    }

    override fun showAsDropDown(anchor: View, xoff: Int, yoff: Int) {
        if (Build.VERSION.SDK_INT >= 24) {
            val rect = Rect()
            anchor.getGlobalVisibleRect(rect)
            val h = anchor.resources.displayMetrics.heightPixels - rect.bottom
            height = h
        }
        super.showAsDropDown(anchor, xoff, yoff)
    }

}