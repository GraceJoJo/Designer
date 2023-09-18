package com.jojo.design.module_core.widgets

import android.content.Context
import androidx.core.widget.NestedScrollView
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.ViewConfiguration

/**
 * author : JOJO
 * e-mail : 18510829974@163.com
 * date   : 2018/12/26 12:53 PM
 * desc   :
 */
class MyNestedScrollView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) : NestedScrollView(context, attrs, defStyleAttr) {
    private var isNeedScroll = true
    private var xDistance: Float = 0.toFloat()
    private var yDistance: Float = 0.toFloat()
    private var xLast: Float = 0.toFloat()
    private var yLast: Float = 0.toFloat()
    private val scaledTouchSlop: Int

    init {
        scaledTouchSlop = ViewConfiguration.get(context).scaledTouchSlop
    }

    override fun onInterceptTouchEvent(ev: MotionEvent): Boolean {
        when (ev.action) {
            MotionEvent.ACTION_DOWN -> {
                yDistance = 0f
                xDistance = yDistance
                xLast = ev.x
                yLast = ev.y
            }
            MotionEvent.ACTION_MOVE -> {
                val curX = ev.x
                val curY = ev.y

                xDistance += Math.abs(curX - xLast)
                yDistance += Math.abs(curY - yLast)
                xLast = curX
                yLast = curY
                //                Log.e("SiberiaDante", "xDistance ：" + xDistance + "---yDistance:" + yDistance);
                return !(xDistance >= yDistance || yDistance < scaledTouchSlop) && isNeedScroll
            }
        }//                if (xDistance > yDistance) {
        //                    return false;
        //                }
        //                return isNeedScroll;
        return super.onInterceptTouchEvent(ev)
    }

    /*
    该方法用来处理NestedScrollView是否拦截滑动事件  isNeedScroll= true 表示MyNestedScrollView消费该事件，false表示给子View消费
     */
    fun setNeedScroll(isNeedScroll: Boolean) {
        this.isNeedScroll = isNeedScroll
    }
}
