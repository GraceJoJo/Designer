package com.jojo.design.common_ui.view;

import android.content.Context;
import android.os.Build;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * 商品详情页底部的RecyclerView
 */
@RequiresApi(api = Build.VERSION_CODES.M)
public class ItemRecyclerView extends RecyclerView implements View.OnScrollChangeListener {
    private float oldX, oldY;
    private int currentPosition;

    @RequiresApi(api = Build.VERSION_CODES.M)
    public ItemRecyclerView(Context context) {
        super(context);
        setOnScrollChangeListener(this);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public ItemRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setOnScrollChangeListener(this);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public ItemRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        setOnScrollChangeListener(this);
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
    public void onScrollChange(View view, int i, int i1, int i2, int i3) {
        LinearLayoutManager layoutManager = (LinearLayoutManager) getLayoutManager();
        currentPosition = layoutManager.findFirstVisibleItemPosition();
    }
}
