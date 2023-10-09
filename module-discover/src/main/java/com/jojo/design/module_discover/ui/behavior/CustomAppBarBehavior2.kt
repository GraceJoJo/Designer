package com.jojo.design.module_discover.ui.behavior

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.appbar.AppBarLayout
import com.jojo.design.common_ui.lrecyclerview.recyclerview.LRecyclerView

/**
 *    @since : 2023/10/7 20:32
 */
class CustomAppBarBehavior2(context: Context, attrs: AttributeSet) :
    AppBarLayout.Behavior(context, attrs) {
    private var isAppBarExpanded = true
    private var isRefreshing = false

    override fun onStartNestedScroll(
        coordinatorLayout: CoordinatorLayout,
        child: AppBarLayout,
        directTargetChild: View,
        target: View,
        axes: Int,
        type: Int
    ): Boolean {
        // 在这里监听RecyclerView的下拉刷新事件
        if (target is RecyclerView && axes == ViewCompat.SCROLL_AXIS_VERTICAL) {
//            isRefreshing = isRecyclerViewRefreshing(target)
        }
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
        child: AppBarLayout,
        target: View,
        dx: Int,
        dy: Int,
        consumed: IntArray,
        type: Int
    ) {
        // 在AppBarLayout处于折叠状态时，拦截RecyclerView的下拉刷新事件
        if (isAppBarExpanded && isRefreshing) {
            consumed[1] = dy
            return
        }
        super.onNestedPreScroll(coordinatorLayout, child, target, dx, dy, consumed, type)
    }

    override fun onDependentViewChanged(
        parent: CoordinatorLayout,
        child: AppBarLayout,
        dependency: View
    ): Boolean {
        // 监听AppBarLayout的展开和折叠状态
        isAppBarExpanded = dependency.bottom >= child.height
        return super.onDependentViewChanged(parent, child, dependency)
    }

//    private fun isRecyclerViewRefreshing(recyclerView: RecyclerView): Boolean {
//        // 根据您的具体实现，判断RecyclerView是否处于下拉刷新状态
//        // 返回true表示处于下拉刷新状态，返回false表示不是
//        // 这里只是一个示例，您需要根据您的实际情况进行判断
//        return (recyclerView as LRecyclerView).isRefreshing
//    }
}