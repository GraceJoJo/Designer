package com.jojo.design.common_ui.view

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.ListView

/**
 *    author : JOJO
 *    e-mail : 18510829974@163.com
 *    date   : 2018/12/10 4:40 PM
 *    desc   : 禁止滑动的ListView
 */
class NoScrollListView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) : ListView(context, attrs, defStyleAttr) {

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val mExpandSpec = View.MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE shr 2, View.MeasureSpec.AT_MOST)
        super.onMeasure(widthMeasureSpec, mExpandSpec)
    }
//
//    @Override
//    public boolean dispatchTouchEvent(MotionEvent ev) {
//        if (ev.getAction() == MotionEvent.ACTION_MOVE) {
//            return true;
//        }
//        return super.dispatchTouchEvent(ev);
//    }
}