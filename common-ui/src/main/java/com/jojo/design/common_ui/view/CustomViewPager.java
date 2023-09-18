package com.jojo.design.common_ui.view;

import android.annotation.SuppressLint;
import android.content.Context;
import androidx.viewpager.widget.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by JoJo on 2018/9/18.
 * wechat:18510829974
 * description:ScrollView嵌套ViewPager的滚动冲突，以及ViewPage中的高度自适应问题解决
 * 解决办法：
 * 1.给ViewPager 设定固定高度（不推荐）
 * 2.自定义ViewPager 重写ViewPager测量方法
 */
public class CustomViewPager extends ViewPager {

    private boolean enabled = true;//false;//默认不可滑动


    public CustomViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        //this.enabled = true;
    }

    //触摸没有反应就可以了
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (this.enabled) {
            try {
                return super.onTouchEvent(event);
            } catch (IllegalArgumentException ex) {
                ex.printStackTrace();
            }
        }

        return false;
    }


    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        if (this.enabled) {
            try {
                return super.onInterceptTouchEvent(event);
            } catch (IllegalArgumentException ex) {
                ex.printStackTrace();
            }
        }

        return false;
    }

    public void setPagingEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    @SuppressLint("UseSparseArrays")
    private Map<Integer, Integer> map = new HashMap<Integer, Integer>();

    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        for (int i = 0; i < getChildCount(); i++) {
            View child = getChildAt(i);
            child.measure(widthMeasureSpec, MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED));
            int h = child.getMeasuredHeight();
            if (h > 0) {
                map.put(i, h);
            }
        }
        Log.e("TAG", "map=" + map.size());
        if (map.size() == 1) {
            Log.e("TAG", "height=" + map.get(0));
        }
        if (map.size() == 2) {
            Log.e("TAG", "height1=" + map.get(0));
            Log.e("TAG", "height2=" + map.get(1));
        }
        if (map.size() == 3) {
            Log.e("TAG", "height1=" + map.get(0));
            Log.e("TAG", "height2=" + map.get(1));
            Log.e("TAG", "height3=" + map.get(2));
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    public Map<Integer, Integer> getMap() {
        return this.map;
    }
}