package com.jojo.design.module_test.behavior

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.View
import androidx.coordinatorlayout.widget.CoordinatorLayout
import com.jojo.design.module_test.R

/**
 *    @author : zhoujuan
 *    @since : 2023/10/8 16:08
 *    @email : zhoujuan.jojo@bytedance.com
 */
class NestedHeaderScrollBehavior constructor(context: Context?, attrs: AttributeSet?) :
    CoordinatorLayout.Behavior<View>(context, attrs) {

    override fun layoutDependsOn(
        parent: CoordinatorLayout,
        child: View,
        dependency: View
    ): Boolean {
        // child: 当前 Behavior 所关联的 View，此处是 HeaderView
        // dependency: 待判断是否需要监听的其他子 View
        return dependency.id == R.id.rv
    }

    override fun onDependentViewChanged(
        parent: CoordinatorLayout,
        child: View,
        dependency: View
    ): Boolean {
//        child.translationY = dependency.translationY * 0.5f
//        child.alpha = 1 + dependency.translationY / (child.height * 0.6f)
        Log.e("JOJO", "dependency.translationY:${dependency.translationY}")
        if (dependency.translationY <= -580.0) {
            child.translationY = -580.0f
        } else {
            child.translationY = dependency.translationY
        }
//        child.alpha = 1 + dependency.translationY / (child.height * 0.6f)
        // 如果改变了 child 的大小位置必须返回 true 来刷新
        return true
    }
}