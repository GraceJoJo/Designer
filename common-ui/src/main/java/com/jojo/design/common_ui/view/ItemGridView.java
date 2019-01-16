package com.jojo.design.common_ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.AbsListView;
import android.widget.GridView;
import android.widget.ListView;

/**
 * 商品详情页底部的GirdView
 */
public class ItemGridView extends GridView implements AbsListView.OnScrollListener {
    private float oldX, oldY;
    private int currentPosition;

    public ItemGridView(Context context) {
        super(context);
        setOnScrollListener(this);
    }

    public ItemGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setOnScrollListener(this);
    }

    public ItemGridView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setOnScrollListener(this);
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
                if (Ys > 0 && currentPosition == 0) {
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
                if (Ys > 0 && currentPosition == 0) {
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

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        currentPosition = getFirstVisiblePosition();
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

    }
}
