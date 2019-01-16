package com.jojo.design.common_ui.view;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v4.view.MotionEventCompat;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.jojo.design.common_ui.R;

/**
 * author : JOJO
 * e-mail : 18510829974@163.com
 * date   : 2019/1/14 4:59 PM
 * desc   : 商品详情页 拖动查看详情
 */
public class DragSlideLayout extends ViewGroup {
    /**
     * Callback for panel OPEN-CLOSE status changed.
     */
    public interface OnSlideDetailsListener {
        /**
         * Called after status changed.
         *
         * @param status {@link Status}
         */
        void onStatucChanged(Status status);
    }

    public enum Status {
        /**
         * Panel is closed
         */
        CLOSE,
        /**
         * Panel is opened
         */
        OPEN;

        public static Status valueOf(int stats) {
            if (0 == stats) {
                return CLOSE;
            } else if (1 == stats) {
                return OPEN;
            } else {
                return CLOSE;
            }
        }
    }

    private static final float DEFAULT_PERCENT = 0.2f;
    private static final int DEFAULT_DURATION = 300;

    private View mFrontView;
    private View mBehindView;

    private float mTouchSlop;
    private float mInitMotionY;
    private float mInitMotionX;


    private View mTarget;
    private float mSlideOffset;
    private Status mStatus = Status.CLOSE;
    private boolean isFirstShowBehindView = true;
    private float mPercent = DEFAULT_PERCENT;
    private long mDuration = DEFAULT_DURATION;
    private int mDefaultPanel = 0;

    private OnSlideDetailsListener mOnSlideDetailsListener;

    public DragSlideLayout(Context context) {
        this(context, null);
    }

    public DragSlideLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DragSlideLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.DragSlideLayout, defStyleAttr, 0);
        mPercent = a.getFloat(R.styleable.DragSlideLayout_percent, DEFAULT_PERCENT);
        mDuration = a.getInt(R.styleable.DragSlideLayout_duration, DEFAULT_DURATION);
        mDefaultPanel = a.getInt(R.styleable.DragSlideLayout_default_panel, 0);
        a.recycle();

        mTouchSlop = ViewConfiguration.get(getContext()).getScaledTouchSlop();
    }

    /**
     * Set the callback of panel OPEN-CLOSE status.
     *
     * @param listener {@link OnSlideDetailsListener}
     */
    public void setOnSlideDetailsListener(OnSlideDetailsListener listener) {
        this.mOnSlideDetailsListener = listener;
    }

    /**
     * Open pannel smoothly.
     *
     * @param smooth true, smoothly. false otherwise.
     */
    public void smoothOpen(boolean smooth) {
        if (mStatus != Status.OPEN) {
            mStatus = Status.OPEN;
            final float height = -getMeasuredHeight();
            animatorSwitch(0, height, true, smooth ? mDuration : 0);
        }
    }

    /**
     * Close pannel smoothly.
     *
     * @param smooth true, smoothly. false otherwise.
     */
    public void smoothClose(boolean smooth) {
        if (mStatus != Status.CLOSE) {
            mStatus = Status.CLOSE;
            final float height = -getMeasuredHeight();
            animatorSwitch(height, 0, true, smooth ? mDuration : 0);
        }
    }

    /**
     * Set the float value for indicate the moment of switch panel
     *
     * @param percent (0.0, 1.0)
     */
    public void setPercent(float percent) {
        this.mPercent = percent;
    }

    @Override
    protected ViewGroup.LayoutParams generateDefaultLayoutParams() {
        return new MarginLayoutParams(MarginLayoutParams.WRAP_CONTENT, MarginLayoutParams.WRAP_CONTENT);
    }

    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new MarginLayoutParams(getContext(), attrs);
    }

    @Override
    protected LayoutParams generateLayoutParams(LayoutParams p) {
        return new MarginLayoutParams(p);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

        final int childCount = getChildCount();
        if (1 >= childCount) {
            throw new RuntimeException("SlideDetailsLayout only accept childs more than 1!!");
        }

        mFrontView = getChildAt(0);
        mBehindView = getChildAt(1);

        // set behindview's visibility to GONE before show.
        //mBehindView.setVisibility(GONE);
        if (mDefaultPanel == 1) {
            post(new Runnable() {
                @Override
                public void run() {
                    smoothOpen(false);
                }
            });
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        final int pWidth = MeasureSpec.getSize(widthMeasureSpec);
        final int pHeight = MeasureSpec.getSize(heightMeasureSpec);

        final int childWidthMeasureSpec =
                MeasureSpec.makeMeasureSpec(pWidth, MeasureSpec.EXACTLY);
        final int childHeightMeasureSpec =
                MeasureSpec.makeMeasureSpec(pHeight, MeasureSpec.EXACTLY);

        View child;
        for (int i = 0; i < getChildCount(); i++) {
            child = getChildAt(i);
            // skip measure if gone
            if (child.getVisibility() == GONE) {
                continue;
            }

            measureChild(child, childWidthMeasureSpec, childHeightMeasureSpec);
        }

        setMeasuredDimension(pWidth, pHeight);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        final int left = l;
        final int right = r;
        int top;
        int bottom;

        final int offset = (int) mSlideOffset;

        View child;
        for (int i = 0; i < getChildCount(); i++) {
            child = getChildAt(i);

            // skip layout
            if (child.getVisibility() == GONE) {
                continue;
            }

            if (child == mBehindView) {
                top = b + offset;
                bottom = top + b - t;
            } else {
                top = t + offset;
                bottom = b + offset;
            }

            child.layout(left, top, right, bottom);
        }
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        ensureTarget();
        if (null == mTarget) {
            return false;
        }

        if (!isEnabled()) {
            return false;
        }

        final int aciton = MotionEventCompat.getActionMasked(ev);

        boolean shouldIntercept = false;
        switch (aciton) {
            case MotionEvent.ACTION_DOWN: {
                mInitMotionX = ev.getX();
                mInitMotionY = ev.getY();
                shouldIntercept = false;
                break;
            }
            case MotionEvent.ACTION_MOVE: {
                final float x = ev.getX();
                final float y = ev.getY();

                final float xDiff = x - mInitMotionX;
                final float yDiff = y - mInitMotionY;

                if (canChildScrollVertically((int) yDiff)) {
                    shouldIntercept = false;
                } else {
                    final float xDiffabs = Math.abs(xDiff);
                    final float yDiffabs = Math.abs(yDiff);

                    // intercept rules：
                    // 1. The vertical displacement is larger than the horizontal displacement;
                    // 2. Panel stauts is CLOSE：slide up
                    // 3. Panel status is OPEN：slide down
                    if (yDiffabs > mTouchSlop && yDiffabs >= xDiffabs
                            && !(mStatus == Status.CLOSE && yDiff > 0
                            || mStatus == Status.OPEN && yDiff < 0)) {
                        shouldIntercept = true;
                    }
                }
                break;
            }
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL: {
                shouldIntercept = false;
                break;
            }

        }

        return shouldIntercept;
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        ensureTarget();
        if (null == mTarget) {
            return false;
        }

        if (!isEnabled()) {
            return false;
        }

        boolean wantTouch = true;
        final int action = MotionEventCompat.getActionMasked(ev);

        switch (action) {
            case MotionEvent.ACTION_DOWN: {
                // if target is a view, we want the DOWN action.
                if (mTarget instanceof View) {
                    wantTouch = true;
                }
                break;
            }

            case MotionEvent.ACTION_MOVE: {
                final float y = ev.getY();
                final float yDiff = y - mInitMotionY;
                if (canChildScrollVertically(((int) yDiff))) {
                    wantTouch = false;
                } else {
                    processTouchEvent(yDiff);
                    wantTouch = true;
                }
                break;
            }

            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL: {
                finishTouchEvent();
                wantTouch = false;
                break;
            }
        }
        return wantTouch;
    }

    /**
     * @param offset Displacement in vertically.
     */
    private void processTouchEvent(final float offset) {
        if (Math.abs(offset) < mTouchSlop) {
            return;
        }

        final float oldOffset = mSlideOffset;
        // pull up to open
        if (mStatus == Status.CLOSE) {
            // reset if pull down
            if (offset >= 0) {
                mSlideOffset = 0;
            } else {
                mSlideOffset = offset;
            }

            if (mSlideOffset == oldOffset) {
                return;
            }

            // pull down to close
        } else if (mStatus == Status.OPEN) {
            final float pHeight = -getMeasuredHeight();
            // reset if pull up
            if (offset <= 0) {
                mSlideOffset = pHeight;
            } else {
                final float newOffset = pHeight + offset;
                mSlideOffset = newOffset;
            }

            if (mSlideOffset == oldOffset) {
                return;
            }
        }
        // relayout
        requestLayout();
    }

    /**
     * Called after gesture is ending.
     */
    private void finishTouchEvent() {
        final int pHeight = getMeasuredHeight();
        final int percent = (int) (pHeight * mPercent);
        final float offset = mSlideOffset;

        boolean changed = false;

        if (Status.CLOSE == mStatus) {
            if (offset <= -percent) {
                mSlideOffset = -pHeight;
                mStatus = Status.OPEN;
                changed = true;
            } else {
                // keep panel closed
                mSlideOffset = 0;
            }
        } else if (Status.OPEN == mStatus) {
            if ((offset + pHeight) >= percent) {
                mSlideOffset = 0;
                mStatus = Status.CLOSE;
                changed = true;
            } else {
                // keep panel opened
                mSlideOffset = -pHeight;
            }
        }

        animatorSwitch(offset, mSlideOffset, changed);
    }

    private void animatorSwitch(final float start, final float end) {
        animatorSwitch(start, end, true, mDuration);
    }

    private void animatorSwitch(final float start, final float end, final long duration) {
        animatorSwitch(start, end, true, duration);
    }

    private void animatorSwitch(final float start, final float end, final boolean changed) {
        animatorSwitch(start, end, changed, mDuration);
    }

    private void animatorSwitch(final float start,
                                final float end,
                                final boolean changed,
                                final long duration) {
        ValueAnimator animator = ValueAnimator.ofFloat(start, end);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                mSlideOffset = (float) animation.getAnimatedValue();
                requestLayout();
            }
        });
        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                if (changed) {
                    if (mStatus == Status.OPEN) {
                        checkAndFirstOpenPanel();
                    }

                    if (null != mOnSlideDetailsListener) {
                        mOnSlideDetailsListener.onStatucChanged(mStatus);
                    }
                }
            }
        });
        animator.setDuration(duration);
        animator.start();
    }

    /**
     * Whether the closed pannel is opened at first time.
     * If open first, we should set the behind view's visibility as VISIBLE.
     */
    private void checkAndFirstOpenPanel() {
        if (isFirstShowBehindView) {
            isFirstShowBehindView = false;
            mBehindView.setVisibility(VISIBLE);
        }
    }

    /**
     * When pulling, target view changed by the panel status. If panel opened, the target is behind view.
     * Front view is for otherwise.
     */
    private void ensureTarget() {
        if (mStatus == Status.CLOSE) {
            mTarget = mFrontView;
        } else {
            mTarget = mBehindView;
        }
    }

    /**
     * Check child view can srcollable in vertical direction.
     *
     * @param direction Negative to check scrolling up, positive to check scrolling down.
     * @return true if this view can be scrolled in the specified direction, false otherwise.
     */
    protected boolean canChildScrollVertically(int direction) {
        if (mTarget instanceof AbsListView) {
            return canListViewSroll((AbsListView) mTarget);
        } else if (mTarget instanceof FrameLayout ||
                mTarget instanceof RelativeLayout ||
                mTarget instanceof LinearLayout) {
            View child;
            for (int i = 0; i < ((ViewGroup) mTarget).getChildCount(); i++) {
                child = ((ViewGroup) mTarget).getChildAt(i);
                if (child instanceof AbsListView) {
                    return canListViewSroll((AbsListView) child);
                }
            }
        }

        if (android.os.Build.VERSION.SDK_INT < 14) {
            return ViewCompat.canScrollVertically(mTarget, -direction) || mTarget.getScrollY() > 0;
        } else {
            return ViewCompat.canScrollVertically(mTarget, -direction);
        }
    }

    protected boolean canListViewSroll(AbsListView absListView) {
        if (mStatus == Status.OPEN) {
            return absListView.getChildCount() > 0
                    && (absListView.getFirstVisiblePosition() > 0 || absListView.getChildAt(0)
                    .getTop() <
                    absListView.getPaddingTop());
        } else {
            final int count = absListView.getChildCount();
            return count > 0
                    && (absListView.getLastVisiblePosition() < count - 1
                    || absListView.getChildAt(count - 1)
                    .getBottom() > absListView.getMeasuredHeight());
        }
    }

    @Override
    protected Parcelable onSaveInstanceState() {
        SavedState ss = new SavedState(super.onSaveInstanceState());
        ss.offset = mSlideOffset;
        ss.status = mStatus.ordinal();
        return ss;
    }

    @Override
    protected void onRestoreInstanceState(Parcelable state) {
        SavedState ss = (SavedState) state;
        super.onRestoreInstanceState(ss.getSuperState());
        mSlideOffset = ss.offset;
        mStatus = Status.valueOf(ss.status);

        if (mStatus == Status.OPEN) {
            mBehindView.setVisibility(VISIBLE);
        }

        requestLayout();
    }

    static class SavedState extends BaseSavedState {

        private float offset;
        private int status;

        /**
         * Constructor used when reading from a parcel. Reads the state of the superclass.
         *
         * @param source
         */
        public SavedState(Parcel source) {
            super(source);
            offset = source.readFloat();
            status = source.readInt();
        }

        /**
         * Constructor called by derived classes when creating their SavedState objects
         *
         * @param superState The state of the superclass of this view
         */
        public SavedState(Parcelable superState) {
            super(superState);
        }

        @Override
        public void writeToParcel(Parcel out, int flags) {
            super.writeToParcel(out, flags);
            out.writeFloat(offset);
            out.writeInt(status);
        }

        public static final Creator<SavedState> CREATOR =
                new Creator<SavedState>() {
                    public SavedState createFromParcel(Parcel in) {
                        return new SavedState(in);
                    }

                    public SavedState[] newArray(int size) {
                        return new SavedState[size];
                    }
                };
    }

}
