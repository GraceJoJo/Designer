package com.jojo.design.module_core;

import android.animation.ValueAnimator;
import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jojo.design.common_ui.lrecyclerview.interfaces.IRefreshHeader;


/**
 * author : JOJO
 * e-mail : 18510829974@163.com
 * date   : 2018/12/12 3:19 PM
 * desc   : 自定义下拉刷新view
 */
public class RefreshView extends LinearLayout implements IRefreshHeader {

    private LinearLayout mContainer;
    //下拉刷新状态的TextView
    private TextView tvStatus;
    //RefreshView的高度
    private int mMeasureHeight;

    public RefreshView(Context context) {
        this(context, null);
    }

    public RefreshView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RefreshView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        initView();
    }

    private void initView() {
        // 初始情况，设置下拉刷新view高度为0
        LayoutParams lp = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        lp.setMargins(0, 0, 0, 0);
        this.setLayoutParams(lp);
        this.setPadding(0, 0, 0, 0);

        mContainer = (LinearLayout) LayoutInflater.from(getContext()).inflate(R.layout.refresh_view, null);
        tvStatus = mContainer.findViewById(R.id.tv);
        addView(mContainer, new LayoutParams(LayoutParams.MATCH_PARENT, 0));
        setGravity(Gravity.BOTTOM);
        //测量RefreshView
        measure(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        mMeasureHeight = getMeasuredHeight();
        Log.e("height", "mMeasureHeight=" + mMeasureHeight); //117
    }

    @Override
    public void onReset() {
        tvStatus.setText("下拉我可以刷新哦...");
    }

    @Override
    public void onPrepare() {
        tvStatus.setText("松开我就马上刷新咯...");
        Log.e("refresh", "onPrepare()");
    }

    @Override
    public void onRefreshing() {
        Log.e("refresh", "onRefreshing()");
    }

    @Override
    public void onMove(float offSet, float sumOffSet) {
        Log.e("refresh", "onMove()");
        tvStatus.setText("别拉我...");
        if (getVisibleHeight() > 0 || offSet > 0) {
            setVisibleHeight((int) offSet + getVisibleHeight());
            Log.e("height", "getVisibleHeight()=" + getVisibleHeight() + "*****mMeasureHeight=" + mMeasureHeight + "*****getMeasuredHeight()=" + getMeasuredHeight());
            if (getVisibleHeight() > mMeasureHeight) {
                onPrepare();
            } else {
                onReset();
            }
        }
    }

    @Override
    public boolean onRelease() {
        Log.e("refresh", "onRelease()");
//        smoothScrollTo(0);
        tvStatus.setText("正在刷新...");
        smoothScrollTo(mMeasureHeight);
        return true;
    }

    @Override
    public void refreshComplete() {
        Log.e("refresh", "refreshComplete()");
        tvStatus.setText("松开我就马上刷新咯...");
        smoothScrollTo(0);
    }


    @Override
    public View getHeaderView() {
        return this;
    }

    @Override
    public int getVisibleHeight() {
        LayoutParams lp = (LayoutParams) mContainer.getLayoutParams();
        return lp.height;
    }

    /**
     * 设置刷新头部的动画
     *
     * @param destHeight
     */
    private void smoothScrollTo(int destHeight) {
        //从当前可见的高度——>到需要回到的高度 执行属性动画
        ValueAnimator animator = ValueAnimator.ofInt(getVisibleHeight(), destHeight);
        animator.setDuration(300).start();
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                setVisibleHeight((int) animation.getAnimatedValue());
            }
        });
        animator.start();
    }

    /**
     * 设置刷新头部可见的高度
     *
     * @param height
     */
    public void setVisibleHeight(int height) {
        if (height < 0) height = 0;
        LayoutParams lp = (LayoutParams) mContainer.getLayoutParams();
        lp.height = height;
        mContainer.setLayoutParams(lp);
    }
}
