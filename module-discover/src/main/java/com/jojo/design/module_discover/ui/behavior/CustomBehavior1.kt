package com.jojo.design.module_discover.ui.behavior

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.View
import androidx.coordinatorlayout.widget.CoordinatorLayout
import com.google.android.material.appbar.AppBarLayout


/**
 *    @since : 2023/10/8 14:12
 */
class CustomBehavior1(context: Context, attrs: AttributeSet) :
    AppBarLayout.ScrollingViewBehavior(context, attrs) {

//    private var isAppBarExpanded = true
//    override fun onDependentViewChanged(
//        parent: CoordinatorLayout,
//        child: View,
//        dependency: View
//    ): Boolean {
//        // 监听AppBarLayout的展开和折叠状态
//        isAppBarExpanded = dependency.bottom >= child.height
//        return super.onDependentViewChanged(parent, child, dependency)
//    }

    override fun onStartNestedScroll(
        coordinatorLayout: CoordinatorLayout,
        child: View, //ViewPager
        directTargetChild: View, //ViewPager
        target: View, //LRecyclerView
        axes: Int,
        type: Int
    ): Boolean {
        Log.e("JOJO", "onStartNestedScroll--------axes:${axes} type:$type  child:${child}  ")
        // 判断是否是向下滑动且AppBarLayout处于折叠状态
//        if (axes == ViewCompat.SCROLL_AXIS_VERTICAL && child is AppBarLayout && child.totalScrollRange != 0) {
//            return true
//        }
//        if (axes === ViewCompat.SCROLL_AXIS_VERTICAL && child is AppBarLayout && (child as AppBarLayout).totalScrollRange != 0) {
//            val viewPager = directTargetChild as ViewPager
//            if (viewPager != null) {
//                val currentItem = viewPager.currentItem
//                val currentView = viewPager.getChildAt(currentItem)
//                if (currentView is RecyclerView) {
//                    return true
//                }
//            }
//        }
//        if(child is ViewPager && coordinatorLayout.getChildAt(0) is AppBarLayout && (coordinatorLayout.getChildAt(0) as AppBarLayout).totalScrollRange !=0){
//            val currentItem = child.currentItem
//            val currentView = child.getChildAt(0)
//            if(currentView is ViewGroup && currentView.getChildAt(0) is RecyclerView){
//                return true
//            }
//        }
        return super.onStartNestedScroll(
            coordinatorLayout,
            child,
            directTargetChild,
            target,
            axes,
            type
        )
    }

    override fun onNestedPreScroll(
        coordinatorLayout: CoordinatorLayout,
        child: View,
        target: View,
        dx: Int,
        dy: Int,
        consumed: IntArray,
        type: Int
    ) {
        Log.e(
            "JOJO",
            "onNestedPreScroll--------child:${child}  target:${target}  dx:${dx}  dy:${dy} type:$type"
        )
        // 如果AppBarLayout处于折叠状态，将滑动事件传递给RecyclerView
//        if (child is AppBarLayout && child.totalScrollRange != 0) {
//            val recyclerView = target as? RecyclerView
//            recyclerView?.let {
//                it.scrollBy(dx, dy)
//                consumed[1] = dy
//            }
//        }
        super.onNestedPreScroll(coordinatorLayout, child, target, dx, dy, consumed, type)
    }
}
