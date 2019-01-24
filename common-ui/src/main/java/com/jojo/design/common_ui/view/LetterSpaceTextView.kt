package com.jojo.design.common_ui.view

import android.content.Context
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ScaleXSpan
import android.util.AttributeSet
import android.widget.TextView

/**
 * author : JOJO
 * e-mail : 18510829974@163.com
 * date   : 2019/1/24 11:21 AM
 * desc   : 可以设置字间距的TextView
 */
class LetterSpaceTextView : TextView {
    var spacing = Spacing.NORMAL
        set(spacing) {
            field = spacing
            applySpacing()
        }
    private var originalText: CharSequence? = ""


    constructor(context: Context) : super(context) {}

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {}

    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(context, attrs, defStyle) {}

    override fun setText(text: CharSequence, type: TextView.BufferType) {
        originalText = text
        applySpacing()
    }

    override fun getText(): CharSequence? {
        return originalText
    }

    private fun applySpacing() {
        if (this == null || this.originalText == null) return
        val builder = StringBuilder()
        for (i in 0 until originalText!!.length) {
            builder.append(originalText!![i])
            if (i + 1 < originalText!!.length) {
                builder.append("\u00A0")
            }
        }
        val finalText = SpannableString(builder.toString())
        if (builder.toString().length > 1) {
            var i = 1
            while (i < builder.toString().length) {
                finalText.setSpan(ScaleXSpan((this.spacing + 1) / 10), i, i + 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
                i += 2
            }
        }
        super.setText(finalText, TextView.BufferType.SPANNABLE)
    }

    object Spacing {
        val NORMAL = 0f
    }
}
