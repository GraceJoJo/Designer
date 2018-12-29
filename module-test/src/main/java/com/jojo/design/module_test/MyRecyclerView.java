package com.jojo.design.module_test;

/**
 * author : JOJO
 * e-mail : 18510829974@163.com
 * date   : 2018/12/29 10:27 AM
 * desc   :
 */

import android.animation.ValueAnimator;
import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.jojo.design.common_base.utils.ScreenUtil;

import java.util.ArrayList;

/**
 * 下拉刷新和加载更多的 自定View
 * Created by SwmIsMe on 2017/3/2.
 */
public class MyRecyclerView extends RecyclerView {

    public final static int STATE_NORMAL = 0;
    public final static int STATE_READY = 1;
    public final static int STATE_REFRESHING = 2;

    MyLoadRefreshAdapter mLoadRefreshAdapter;
    View headerView, footerView;

    private int mState = STATE_NORMAL;
    //    头布局的高度
    int headerViewHeight;
    //    是否正在触摸
    boolean isOnTouching;
    TextView status;
    boolean isRefresh;
    private LinearLayout mLl;
    private LinearLayout.LayoutParams mParams;
    private LayoutManager mLayoutManager;

    public MyRecyclerViewListener getMyRecyclerViewListener() {
        return myRecyclerViewListener;
    }

    //    设置 监听
    public void setMyRecyclerViewListener(MyRecyclerViewListener myRecyclerViewListener) {
        this.myRecyclerViewListener = myRecyclerViewListener;
    }

    MyRecyclerViewListener myRecyclerViewListener;

    public MyRecyclerView(Context context) {
        super(context);
    }

    public MyRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MyRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    int lastX, lastY;

    @Override
    public boolean onTouchEvent(MotionEvent e) {
        int x = (int) e.getX();
        int y = (int) e.getY();

        switch (e.getAction()) {
            case MotionEvent.ACTION_DOWN:
                break;
            case MotionEvent.ACTION_MOVE:
                isOnTouching = true;
//                获取到 轮播的view
                View view1 = this.getChildAt(1);
//                获取到  轮播的view安排的位置.
                int loopViewPagerPosition = this.getChildLayoutPosition(view1);
//                如果 轮播的view安排(layout)的位置. 是第一个 触发下拉刷新的操作.
                if (loopViewPagerPosition == 1) {
                    if (lastY == 0) {
                        lastY = y;
                    }
                    int dy = lastY - y;
                    int dx = lastX - x;
                    int yabs = Math.abs(dy);
                    int xabs = Math.abs(dx);
                    if (yabs > xabs) {
                        isRefresh = true;
                        changeHeight(dy);
                        this.requestDisallowInterceptTouchEvent(true);
                    } else {
                        this.requestDisallowInterceptTouchEvent(false);
                    }
                    lastX = x;
                    lastY = y;
                }
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                isRefresh = false;
                isOnTouching = false;
//                归零
                lastY = 0;
//                开始刷新
                if (mState == STATE_READY || mState == STATE_REFRESHING) {
                    onStatusChange(STATE_REFRESHING);
                    autoSize(0);
                }
//                回归到 隐藏的位置
                if (mState == STATE_NORMAL) {
                    autoSize(-headerViewHeight);
                }
                break;
        }
        return super.onTouchEvent(e);
    }

    //    通过改变 topMargin的值.改变高度
    private void changeHeight(int dy) {
        mParams.topMargin -= dy;
        int margin = mParams.topMargin;
        mLl.setLayoutParams(mParams);
        setStateByHeight(margin, false);
    }

    /**
     * 更新 头布局的高度
     *
     * @param targetHeight 要改变到的高度  当这个高度为0时,topMargin为0,显示整个刷新的界面,
     *                     -headerViewHeight  隐藏头布局
     */
    public void autoSize(int targetHeight) {
        int currentHeight = mParams.topMargin;
//        从当前高度 到 目前高度,的动画变化值.
        ValueAnimator objectAnimator = ValueAnimator.ofInt(currentHeight, targetHeight);
//        设置下拉刷新的高度
        objectAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int animatedValue = (int) animation.getAnimatedValue();
//                根据高度设置文本
                setStateByHeight(animatedValue, true);
                mParams.topMargin = animatedValue;
                mLl.setLayoutParams(mParams);
            }
        });
        objectAnimator.start();
    }


    //    设置加载更多的判断
    @Override
    public void setLayoutManager(final LayoutManager layout) {
        super.setLayoutManager(layout);

        this.addOnScrollListener(new OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }


            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (isRefresh) {
                    return;
                }
                if (mState != STATE_NORMAL) {
                    return;
                }
                Log.e("TAG", "new state" + newState);
                //判断是否最后一item个显示出来
                mLayoutManager = getLayoutManager();
                //可见的item个数
                int visibleChildCount = mLayoutManager.getChildCount();
//                如果大于0 并且 状态不等于空闲 并且 没有在加载更多
                if (visibleChildCount > 0 && newState == RecyclerView.SCROLL_STATE_IDLE && !isLoadMore) {
//                    获取最后一个 view 除加载更多view
                    View lastVisibleView = recyclerView.getChildAt(recyclerView.getChildCount() - 1);
//                    返回给定子视图的适配器位置
                    int lastVisiblePosition = recyclerView.getChildLayoutPosition(lastVisibleView);
//                    如果 最后一个可见的位置 大于等于
                    if (lastVisiblePosition >= mLayoutManager.getItemCount() - 1) {
//                        加载更多view 可见
                        footerView.setVisibility(VISIBLE);
                        isLoadMore = true;
//                        加载更多
                        if (myRecyclerViewListener != null) {
                            myRecyclerViewListener.onLoadMore();
                        }
                    } else {
                        footerView.setVisibility(GONE);
                    }
                }

            }
        });
    }

    //    根据高度来设置 现在的状态
    private void setStateByHeight(int height, boolean isAuto) {
        if (mState == STATE_REFRESHING) {
            return;
        }
//        如果 当前的高度 小于 头布局的高度
        if (height < headerViewHeight) {
            onStatusChange(STATE_NORMAL);
        } else if (height > headerViewHeight) {
            onStatusChange(STATE_READY);
        } else if (height == headerViewHeight && !isOnTouching && !isAuto) {
            onStatusChange(STATE_REFRESHING);
        }
    }

    boolean isLoadMore;

    public void onStatusChange(int status) {
        mState = status;
        switch (status) {
            case STATE_READY:
                this.status.setText("松开刷新...");
                break;
            case STATE_NORMAL:
                this.status.setText("下拉刷新...");
                break;
            case STATE_REFRESHING:
                this.status.setText("正在刷新...");
                if (myRecyclerViewListener != null) {
                    myRecyclerViewListener.onRefresh();
                }
                break;
        }
    }

    //    初始化头尾布局 和 头尾view的适配器
    @Override
    public void setAdapter(Adapter adapter) {
        ArrayList<View> headers = new ArrayList<>();
        ArrayList<View> footers = new ArrayList<>();
//        加载下拉刷新的布局文件
        View refreshView = LayoutInflater.from(getContext()).inflate(R.layout.item_recycler_refresh, null);
        headerView = refreshView;
        status = (TextView) refreshView.findViewById(R.id.status);
        mLl = (LinearLayout) refreshView.findViewById(R.id.ll);
        headerView.post(new Runnable() {
            @Override
            public void run() {
//                头布局真正的高度
                headerViewHeight = headerView.getHeight();
//                设置layout参数  隐藏头布局
                mParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                mParams.topMargin = -headerViewHeight;
                mParams.width = 720;
                mLl.setLayoutParams(mParams);
            }
        });
//        将头布局添加到 集合中
        headers.add(headerView);

//        初始化 尾布局
        LinearLayout footerLayout = new LinearLayout(getContext());
        footerLayout.setGravity(Gravity.CENTER);
        footerLayout.setLayoutParams(new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        footers.add(footerLayout);
        footerLayout.setPadding(0, 50, 0, 50);
        footerLayout.addView(new ProgressBar(getContext(), null, android.R.attr.progressBarStyleSmall));

        TextView text = new TextView(getContext());
        text.setText("正在加载...");
        footerLayout.addView(text);
        footerView = footerLayout;
        footerView.setVisibility(GONE);

//        初始化 适配器
        mLoadRefreshAdapter = new MyLoadRefreshAdapter(adapter, headers, footers);
        super.setAdapter(mLoadRefreshAdapter);
    }

    public interface MyRecyclerViewListener {
        void onRefresh();

        void onLoadMore();
    }

    //    完成加载更多
    public void setLoadMoreComplete() {
//        直接隐藏 尾布局
        footerView.setVisibility(GONE);
        isLoadMore = false;
    }

    //    完成下拉刷新
    public void setRefreshComplete() {
        onStatusChange(STATE_NORMAL);
        autoSize(-headerViewHeight);
    }
}
