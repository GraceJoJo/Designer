package com.jojo.design.common_ui.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import androidx.core.content.ContextCompat;
import android.text.Layout;
import android.text.Selection;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.BackgroundColorSpan;
import android.text.style.ClickableSpan;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import com.jojo.design.common_ui.R;


/**
 * Created by JoJo on 2019/1/15.
 * wechat:18510829974
 * description: 查看更多的TextView
 */

public class OverTextView extends androidx.appcompat.widget.AppCompatTextView {
    private Context mContext;
    /**
     * 文字超出时的提示信息
     */
    private String mTips = "查看更多";

    private OnCustomLinkClickListener mOnCustomLinkClickListener;

    // 点击后背景颜色显示
    private int mBackColor = -1;

    private int mStart = -1;
    private int mEnd = -1;

    public OverTextView(Context context) {
        this(context,null);
    }

    public OverTextView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public OverTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.mContext = context;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        boolean result = super.onTouchEvent(event);
        int action = event.getAction();

        int x = (int) event.getX();
        int y = (int) event.getY();

        x -= getTotalPaddingLeft();
        y -= getTotalPaddingTop();

        x += getScrollX();
        y += getScrollY();

        Layout layout = getLayout();
        // 获取当前点击位置所在的行
        int line = layout.getLineForVertical(y);
        // 获取当前点击位置的文本的偏移值
        int off = layout.getOffsetForHorizontal(line, x);

        CharSequence text = getText();
        if (TextUtils.isEmpty(text) || !(text instanceof Spannable)) {
            return result;
        }

        Spannable buffer = (Spannable) text;
        ClickableSpan[] link = buffer.getSpans(off, off, ClickableSpan.class);

        if (link.length != 0) {
//            if (action == MotionEvent.ACTION_DOWN) {
//                mStart = buffer.getSpanStart(link[0]);
//                mEnd = buffer.getSpanEnd(link[0]);
//                if (mStart >= 0 && mEnd >= mStart) {
//                    buffer.setSpan(new BackgroundColorSpan(getBackColor()),
//                            mStart, mEnd, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
//                }
//            } else if (action == MotionEvent.ACTION_UP
//                    || action == MotionEvent.ACTION_CANCEL) {
//
//                if (mStart >= 0 && mEnd >= mStart) {
//                    buffer.setSpan(new BackgroundColorSpan(Color.TRANSPARENT),
//                            mStart, mEnd, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
//
//                    mStart = -1;
//                    mEnd = -1;
//                }
//            }

            return true;
        } else {
            if (mStart >= 0 && mEnd >= mStart) {
                buffer.setSpan(new BackgroundColorSpan(Color.TRANSPARENT),
                        mStart, mEnd, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                mStart = -1;
                mEnd = -1;
            }
            Selection.removeSelection(buffer);
            return false;
        }
    }

    /**
     * 文本宽度超出TextView的范围的具体项值
     *
     * @param0表示不考虑提示文本
     * @return -1 表示没有超过TextView的范围。
     */
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    private int starOverFlow(String text, float tipsWidth) {
        if (TextUtils.isEmpty(text)) {
            return -1;
        }
        text=text.trim();
        TextPaint paint = getPaint();
        // 文字总宽度
        float width = paint.measureText(text);
        float rangeWidth = 0;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            rangeWidth = getLayout().getWidth() * getMaxLines() - tipsWidth;
        } else {
            rangeWidth = getLayout().getWidth() * getLayout().getLineCount()
                    - tipsWidth;
        }
        if (width > rangeWidth) {
         /*
          * \u2026为文本超出时系统显示的省略号
          */
            return paint.breakText(text, true,
                    rangeWidth - paint.measureText("\u2026"), null);
        } else {
            return -1;
        }
    }

    /**
     * 测量文字宽度
     *
     * @param text
     * @return
     */
    private float measureTextWidth(String text) {
        if (TextUtils.isEmpty(text)) {
            return 0;
        }
        TextPaint paint = getPaint();
        return paint.measureText(text);
    }

    /**
     * 替换文本
     *
     * 请在该控件显示后调用
     *
     * @param tips
     *            要替换的提示文本
     */
    public void replaceTips(String tips) {
        if (TextUtils.isEmpty(tips)) {
            tips = mTips;
        }
        // 算出提示文本附加的位置
        int index = starOverFlow(getText().toString(), measureTextWidth(tips.trim()));
        if (index > 0) {
            /**
             * 多截取几个，避免显示不统一（有些文本时，提示文本可能会显示不全）
             */
            String sl="…";//此省略号为中文的。
            String willText = getText().toString()
                    .substring(0, index-sl.length());
            willText = willText +"……"+ tips;
            SpannableStringBuilder s = new SpannableStringBuilder(willText);
            SpannableString style = new SpannableString(s);
            // style.setSpan(new ForegroundColorSpan(Color.BLUE),
            // willText.indexOf(tips), willText.length(),
            // Spanned.SPAN_INCLUSIVE_INCLUSIVE);
            MyClickSpan myClickSpan = new MyClickSpan();
            style.setSpan(myClickSpan, willText.indexOf(tips),
                    willText.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            setMovementMethod(LinkMovementMethod.getInstance());
            setText(style);

            // // 标识文本可被编辑
            // setText(willText, BufferType.EDITABLE);
            // Editable et = getEditableText();
            // et.setSpan(new ForegroundColorSpan(Color.BLUE),
            // willText.indexOf(tips), willText.length(),
            // Spanned.SPAN_INCLUSIVE_INCLUSIVE);
        }
    }

    /**
     * 获取点击后背景颜色显示，默认灰色
     *
     * @return
     */
    public int getBackColor() {
        if (mBackColor == -1) {
            return Color.GRAY;
        }
        return mBackColor;
    }

    /**
     * 设置点击后背景颜色
     *
     * @param mBackColor
     *            请勿填写颜色资源ID
     */
    public void setBackColor(int mBackColor) {
        this.mBackColor = mBackColor;
    }

    /**
     * 设置点击后背景颜色
     *
     * @param mBackColor
     *            颜色值 如：#000000 #00000000 或者 green red 的颜色名称
     */
    public void setBackColor(String mBackColor) {
        if (!TextUtils.isEmpty(mBackColor)) {
            this.mBackColor = Color.parseColor(mBackColor);
        }
    }

    /**
     * 使用颜色资源ID设置点击后背景颜色
     *
     * @param resId
     *            资源ID值
     */
    public void setBackColorByRes(int resId) {
        try {
            this.mBackColor = getContext().getResources().getColor(resId);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 设置自定义的链接的点击事件
     *
     * @param mOnCustomLinkClickListener
     */
    public void setOnCustomLinkClickListener(
            OnCustomLinkClickListener mOnCustomLinkClickListener) {
        this.mOnCustomLinkClickListener = mOnCustomLinkClickListener;
    }

    /**
     * 替换文本
     *
     * 请在该控件显示后调用
     */
    public void replaceTips() {
        replaceTips(null);
    }

    public class MyClickSpan extends ClickableSpan {

        @Override
        public void onClick(View arg0) {
            Spannable spannable = (Spannable) ((TextView) arg0).getText();
            Selection.removeSelection(spannable);
            if (mOnCustomLinkClickListener != null) {
                mOnCustomLinkClickListener.onCustomLinkClick();
            }
        }

        @Override
        public void updateDrawState(TextPaint ds) {
            ds.setUnderlineText(false);
            ds.setColor(ContextCompat.getColor(mContext, R.color.transparent));
        }
    }

    /**
     * 自定义的链接点击事件
     *
     * @author frj
     *
     */
    public interface OnCustomLinkClickListener {
        /**
         * 自定义链接点击
         */
        void onCustomLinkClick();
    }

}