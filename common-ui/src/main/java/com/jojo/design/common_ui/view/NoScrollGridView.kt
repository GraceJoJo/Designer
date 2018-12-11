package com.jojo.design.common_ui.view

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.GridView
import android.widget.ListView

/**
 *    author : JOJO
 *    e-mail : 18510829974@163.com
 *    date   : 2018/12/10 4:44 PM
 *    desc   : 禁止滑动的GridView
 */
class NoScrollGridView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) : GridView(context, attrs, defStyleAttr) {
    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val expandSpec = View.MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE shr 2,
                View.MeasureSpec.AT_MOST)
        super.onMeasure(widthMeasureSpec, expandSpec)
    }
}