package com.jojo.design.common_ui.view;

import android.content.Context;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * desc:
 * author:huangxy
 * date:2018/7/23
 */
public class VerticalTopRecyclerView extends RecyclerView {

    boolean allowDragBottom = true; // 如果是true，则允许拖动至底部的下一页
    float downY = 0;
    boolean needConsumeTouch = true; // 是否需要承包touch事件，needConsumeTouch一旦被定性，则不会更改

    public VerticalTopRecyclerView(Context context) {
        this(context, null);
    }

    public VerticalTopRecyclerView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public VerticalTopRecyclerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            downY = ev.getRawY();
            needConsumeTouch = true; // 默认情况下，RecyclerView内部的滚动优先，默认情况下由该RecyclerView去消费touch事件
            allowDragBottom = isAtBottom();
        } else if (ev.getAction() == MotionEvent.ACTION_MOVE) {
            allowDragBottom = isAtBottom();

            if (!needConsumeTouch) {
                // 在最顶端且向上拉了，则这个touch事件交给父类去处理
                getParent().requestDisallowInterceptTouchEvent(false);
                return false;
            } else if (allowDragBottom) {
                // needConsumeTouch尚未被定性，此处给其定性
                // 允许拖动到底部的下一页，而且又向上拖动了，就将touch事件交给父view
                if (downY - ev.getRawY()> 2) {
                    // flag设置，由父类去消费
                    needConsumeTouch = false;
                    getParent().requestDisallowInterceptTouchEvent(false);
                    return false;
                }
            }
        }

        // 通知父view是否要处理touch事件
        getParent().requestDisallowInterceptTouchEvent(needConsumeTouch);
        return super.dispatchTouchEvent(ev);
    }

    /**
     * 判断listView是否在顶部
     *
     * @return 是否在顶部
     */
    private boolean isAtBottom() {
        boolean resultValue = false;
        int childNum = getChildCount();
        if (childNum == 0) {
            // 没有child，肯定在顶部
            resultValue = true;
        } else {
            LayoutManager layoutManager = getLayoutManager();
            if (layoutManager instanceof LinearLayoutManager) {
                LinearLayoutManager manager = (LinearLayoutManager) layoutManager;
                int childCount = manager.getItemCount();
                if (manager.findLastVisibleItemPosition() == childCount-1) {
                    // 根据第一个childView来判定是否在顶部
                    View lastView = manager.getChildAt(childNum -1);
                    if (Math.abs(lastView.getBottom() - getBottom()) < 2) {
                        resultValue = true;
                    }
                }
            }
        }
        return resultValue;
    }
}
