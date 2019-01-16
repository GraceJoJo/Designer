package com.jojo.design.common_ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.ScrollView;

/**
 * author : JOJO
 * e-mail : 18510829974@163.com
 * date   : 2019/1/16 3:03 PM
 * desc   :
 */
public class ItemScrollView extends MyScrollView {
    public float oldY;
    private int t;
    private float oldX;

    public ItemScrollView(Context context) {
        super(context);
    }

    public ItemScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ItemScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_MOVE:
                float Y = ev.getY();
                float Ys = Y - oldY;
                float X = ev.getX();
                int[] location = new int[2];
                getLocationInWindow(location);

                //滑动到顶部让父控件重新获得触摸事件
                if (Ys > 0 && getScrollY() == 0) {
                    getParent().getParent().requestDisallowInterceptTouchEvent(false);
                }
                break;

            case MotionEvent.ACTION_DOWN:
                getParent().getParent().requestDisallowInterceptTouchEvent(true);
                oldY = ev.getY();
                oldX = ev.getX();
                break;

            case MotionEvent.ACTION_UP:
                getParent().getParent().requestDisallowInterceptTouchEvent(true);
                break;

            default:
                break;
        }
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_MOVE:
                float Y = ev.getY();
                float Ys = Y - oldY;
                float X = ev.getX();
                int[] location = new int[2];
                getLocationInWindow(location);

                //滑动到顶部让父控件重新获得触摸事件
                if (Ys > 0 && getScrollY() == 0) {
                    getParent().getParent().requestDisallowInterceptTouchEvent(false);
                }
                break;

            case MotionEvent.ACTION_DOWN:
                getParent().getParent().requestDisallowInterceptTouchEvent(true);
                oldY = ev.getY();
                oldX = ev.getX();
                break;

            case MotionEvent.ACTION_UP:
                getParent().getParent().requestDisallowInterceptTouchEvent(true);
                break;

            default:
                break;
        }
        return super.onTouchEvent(ev);
    }
}
