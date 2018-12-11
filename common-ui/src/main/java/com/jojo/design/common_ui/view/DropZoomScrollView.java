package com.jojo.design.common_ui.view;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.TranslateAnimation;
import android.widget.ScrollView;

/**
 * author : JOJO
 * e-mail : 18510829974@163.com
 * date   : 2018/12/9 5:19 PM
 * desc   : 下拉放大scrollView
 */
public class DropZoomScrollView extends MyScrollView {
    private static final String TAG = "DropZoomScrollView";
    //----头部收缩属性--------
    // 记录首次按下位置
    private float mFirstPosition = 0;
    // 头部图片是否正在放大
    private Boolean mScaling = false;
    private View dropZoomView;//需要被放大的view
    private int dropZoomViewWidth;
    private int dropZoomViewHeight;
    //----头部收缩属性end--------
    //------尾部收缩属性--------
    private View inner;// 子View
    private float y;// 点击时y坐标
    private Rect normal = new Rect();// 矩形(这里只是个形式，只是用于判断是否需要动画.)
    private boolean isCount = false;// 是否开始计算
    //最后的坐标
    private float lastX = 0;
    private float lastY = 0;
    //当前坐标
    private float currentX = 0;
    private float currentY = 0;
    //移动的坐标量
    private float distanceX = 0;
    private float distanceY = 0;
    private boolean upDownSlide = false; //判断上下滑动的flag
    private float ratio = 0.5f; //滚动响应距离xi'shu

    //------尾部收缩属性end--------
    public DropZoomScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    //初始化
    private void init() {
        setOverScrollMode(OVER_SCROLL_NEVER);
        if (getChildAt(0) != null) {
            inner = getChildAt(0);//这个是底部收缩的view
            //头部收缩的
            ViewGroup vg = (ViewGroup) getChildAt(0);
            if (vg.getChildAt(0) != null) {
                dropZoomView = vg.getChildAt(0);
            }
        }
    }

    /***
     * 生成视图工作完成.该函数在生成视图的最后调用，在所有子视图添加完之后. 即使子类覆盖了 onFinishInflate
     * 方法，也应该调用父类的方法，使该方法得以执行.
     */
    @Override
    protected void onFinishInflate() {
        //初始化
        init();
        super.onFinishInflate();
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        //这里只是计算尾部坐标
        currentX = ev.getX();
        currentY = ev.getY();
        switch (ev.getAction()) {
            case MotionEvent.ACTION_MOVE:
                distanceX = currentX - lastX;
                distanceY = currentY - lastY;
                if (Math.abs(distanceX) < Math.abs(distanceY) && Math.abs(distanceY) > 12) {
                    upDownSlide = true;
                }
                break;
        }
        lastX = currentX;
        lastY = currentY;
        if (upDownSlide && inner != null) commOnTouchEvent(ev);
        return super.dispatchTouchEvent(ev);
    }

    /***
     * 触摸事件
     *
     * @param ev
     */
    public void commOnTouchEvent(MotionEvent ev) {
        //头部缩放计算
        if (dropZoomViewWidth <= 0 || dropZoomViewHeight <= 0) {
            dropZoomViewWidth = dropZoomView.getMeasuredWidth();
            dropZoomViewHeight = dropZoomView.getMeasuredHeight();
        }
        switch (ev.getAction()) {
            case MotionEvent.ACTION_UP:
                //手指离开后头部恢复图片
                mScaling = false;
                replyImage();
                // 手指松开尾部恢复
                if (isNeedAnimation()) {
                    animation();
                    isCount = false;
                }
                clear0();
                break;
            //这里头尾分开处理，互不干扰
            case MotionEvent.ACTION_MOVE:
                //尾部处理
                final float preY = y;// 按下时的y坐标
                float nowY = ev.getY();// 时时y坐标
                int deltaY = (int) (preY - nowY);// 滑动距离
                if (!isCount) {
                    deltaY = 0; // 在这里要归0.
                }
                y = nowY;
                // 当滚动到最上或者最下时就不会再滚动，这时移动布局
                if (isNeedMove()) {
                    // 初始化头部矩形
                    if (normal.isEmpty()) {
                        // 保存正常的布局位置
                        normal.set(inner.getLeft(), inner.getTop(),
                                inner.getRight(), inner.getBottom());
                    }
                    // 移动布局 手指移动的距离/3表示布局响应的距离是移动的距离的1/3
                    inner.layout(inner.getLeft(), inner.getTop() - deltaY / 3,
                            inner.getRight(), inner.getBottom() - deltaY / 3);
                }
                isCount = true;
                //尾部处理end
                //头部处理
                if (!mScaling) {
                    //getScrollY() ScrollView滚动的距离
                    if (getScrollY() == 0) {
                        //记录手指触摸屏幕的位置
                        mFirstPosition = ev.getY();// 滚动到顶部时记录位置，否则正常返回
                    } else {
                        break;
                    }
                }
                int distance = (int) ((ev.getY() - mFirstPosition) * ratio); // 滚动距离乘以一个系数
                if (distance < 0) { // 当前位置比记录位置要小，正常返回
                    break;
                }
                // 处理放大
                mScaling = true;
                setZoom(1 + distance);
                //头部处理end
                break;
        }
    }

    /***
     * 回缩动画,尾部往下缩动画
     */
    public void animation() {
        // 开启移动动画
        TranslateAnimation ta = new TranslateAnimation(0, 0, inner.getTop(),
                normal.top);
        ta.setDuration(200);
        inner.startAnimation(ta);
        // 设置回到正常的布局位置
        inner.layout(normal.left, normal.top, normal.right, normal.bottom);
        normal.setEmpty();
    }

    // 是否需要开启动画
    public boolean isNeedAnimation() {
        return !normal.isEmpty();
    }

    // 回弹动画，header往上缩动画 (使用了属性动画)
    public void replyImage() {
        final float distance = dropZoomView.getMeasuredWidth() - dropZoomViewWidth;
        // 设置动画
        ValueAnimator anim = ObjectAnimator.ofFloat(0.0F, 1.0F).setDuration((long) (distance * 0.7));
        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                //从0-1变化
                float cVal = (Float) animation.getAnimatedValue();
                setZoom(distance - ((distance) * cVal));
            }
        });
        anim.start();
    }

    //头部缩放
    public void setZoom(float s) {
        if (dropZoomViewHeight <= 0 || dropZoomViewWidth <= 0) {
            return;
        }
        ViewGroup.LayoutParams lp = dropZoomView.getLayoutParams();
        lp.width = (int) (dropZoomViewWidth + s);
        lp.height = (int) (dropZoomViewHeight * ((dropZoomViewWidth + s) / dropZoomViewWidth));
        //设置居中放大(只对ScrollView的第一个子View是线性布局的情况下起作用，非线性布局，需要在布局文件中设置dropZoomView是水平居中的，下拉放大才是居中放大的效果，否则只会偏右边放大)
        ((MarginLayoutParams) lp).setMargins(-(lp.width - dropZoomViewWidth) / 2, 0, 0, 0);
        dropZoomView.setLayoutParams(lp);
    }

    /***
     * 是否需要移动布局 inner.getMeasuredHeight():获取的是控件的总高度
     *
     * getHeight()：获取的是屏幕的高度
     *
     * @return
     */
    public boolean isNeedMove() {
        int offset = inner.getMeasuredHeight() - getHeight();
        int scrollY = getScrollY();
        // 0是顶部，后面那个是底部
        if (scrollY == 0 || scrollY == offset) {
            return true;
        }
        return false;
    }

    //清理尾部属性值
    private void clear0() {
        lastX = 0;
        lastY = 0;
        distanceX = 0;
        distanceY = 0;
        upDownSlide = false;
    }
}