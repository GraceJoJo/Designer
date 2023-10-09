package com.jojo.design.module_discover.ui.behavior

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.widget.LinearLayout
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.appbar.AppBarLayout
import com.jojo.design.common_ui.lrecyclerview.recyclerview.LRecyclerView
import com.jojo.design.module_discover.R


/**
 * Android CoordinatorLayout 自定义 Behavior：https://blog.51cto.com/u_15375308/5073098
 * 自定义Behavior支持嵌套滑动时Tab吸顶（可用）
 */
class CustomBehavior constructor(context: Context?, attrs: AttributeSet?) :
    CoordinatorLayout.Behavior<View>(context, attrs) {
    private var headerHeight = 0
    private var totalScrollRange = 0f

    //child: ViewPager
    override fun onLayoutChild(
        parent: CoordinatorLayout,
        child: View,
        layoutDirection: Int
    ): Boolean {
        // 首先让父布局按照标准方式解析
        parent.onLayoutChild(child, layoutDirection)
        // 获取到 HeaderView 的高度
        headerHeight = parent.findViewById<AppBarLayout>(R.id.appbar).measuredHeight
        // 设置 top 从而排在 HeaderView的下面
        ViewCompat.offsetTopAndBottom(child, headerHeight)
        return true // true 表示我们自己完成了解析 不要再自动解析了
    }

    override fun layoutDependsOn(
        parent: CoordinatorLayout,
        child: View,
        dependency: View
    ): Boolean {
        // child: 当前 Behavior 所关联的 View，此处是 HeaderView
        // dependency: 待判断是否需要监听的其他子 View
//        return dependency.id == R.id.viewpager
        return dependency is AppBarLayout
    }

    //child: ViewPager
    //dependency: AppBarLayout
    override fun onDependentViewChanged(
        parent: CoordinatorLayout,
        child: View,
        dependency: View
    ): Boolean {
//        Log.e("JOJO", "onDependentViewChanged-----dependency.top:${dependency.top}")
        child.translationY = dependency.top.toFloat()
        // 如果改变了 child 的大小位置必须返回 true 来刷新
        return true
    }

    override fun onStartNestedScroll(
        coordinatorLayout: CoordinatorLayout, child: View, directTargetChild: View,
        target: View, axes: Int, type: Int
    ): Boolean {
        // 如果是垂直滑动的话就声明需要处理
        // 只有这里返回 true 才会收到下面一系列滑动事件的回调
        return (axes and ViewCompat.SCROLL_AXIS_VERTICAL) != 0
    }

//    override fun onInterceptTouchEvent(
//        parent: CoordinatorLayout,
//        child: View,
//        ev: MotionEvent
//    ): Boolean {
//                //AppBarLayout的可滑动距离
//        totalScrollRange = (parent.getChildAt(0) as? AppBarLayout)?.totalScrollRange?.toFloat() ?: 0f
//        Log.e("TTA","child:${child.translationY} totalScrollRange:${totalScrollRange}")
////        return true
//        return super.onInterceptTouchEvent(parent, child, ev)
//    }

    //child: ViewPager
    //target: RecyclerView
//    override fun onNestedPreScroll(
//        coordinatorLayout: CoordinatorLayout, child: View, target: View, dx: Int, dy: Int,
//        consumed: IntArray, type: Int
//    ) {
//        // 此时 RecyclerView 还没开始滑动
//        super.onNestedPreScroll(coordinatorLayout, child, target, dx, dy, consumed, type)
//        Log.e(
//            "JOJO",
//            "onNestedPreScroll----child.translationY:${child.translationY} dy:${dy}  top:${child.top} headerHeight:${headerHeight} child.totalScrollRange:${
//                (coordinatorLayout.getChildAt(
//                    0
//                ) as? AppBarLayout)?.totalScrollRange
//            }"
//        )
//        //AppBarLayout的可滑动距离
//        totalScrollRange =
//            (coordinatorLayout.getChildAt(0) as? AppBarLayout)?.totalScrollRange?.toFloat() ?: 0f
//
//        //针对手指下滑做特殊处理
//        if (dy < 0 && Math.abs(child.translationY) <= totalScrollRange) {
////            child.translationY = -465f
//            val recyclerView = target as? RecyclerView
//            recyclerView?.let {
//                it.scrollBy(dx, dy)
//                consumed[1] = dy
//            }
//        }
//        super.onNestedPreScroll(coordinatorLayout, child, target, dx, dy, consumed, type)
//    }

    //child: ViewPager
    //target: RecyclerView
//    override fun onNestedScroll(
//        coordinatorLayout: CoordinatorLayout,
//        child: View,
//        target: View,
//        dxConsumed: Int,
//        dyConsumed: Int,
//        dxUnconsumed: Int,
//        dyUnconsumed: Int,
//        type: Int
//    ) {
//
//        // 此时 RV 已经完成了滑动，dyUnconsumed 表示剩余未消耗的滑动距离
//        super.onNestedScroll(
//            coordinatorLayout,
//            child,
//            target,
//            dxConsumed,
//            dyConsumed,
//            dxUnconsumed,
//            dyUnconsumed,
//            type
//        )
//        if (dyUnconsumed < 0) { // 只处理手指向下滑动的情况
//
//            val recyclerView = target as? LRecyclerView
//            recyclerView?.let {
//                it.refresh()
//            }
//        }
//
//    }

}
