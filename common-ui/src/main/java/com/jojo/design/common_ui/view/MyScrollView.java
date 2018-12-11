package com.jojo.design.common_ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ScrollView;

/**
 * Created by JoJo on 2018/4/20.
 * wechat:18510829974
 * description: 使支持API 23 Android 6ScrollView
 */

public class MyScrollView extends ScrollView {
    public MyScrollView(Context context) {
        this(context, null);
    }

    public MyScrollView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        if (mListener != null) {
            mListener.onScrollChanged(l, t, oldl, oldt);
        }
    }

   private OnScrollViewListener mListener;

    public interface OnScrollViewListener {
        void onScrollChanged(int scrollX, int scrollY, int oldx, int oldY);
    }

    public void setOnScrollViewListener(OnScrollViewListener listener) {
        mListener = listener;
    }
}
