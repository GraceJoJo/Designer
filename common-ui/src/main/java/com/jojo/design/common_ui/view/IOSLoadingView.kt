package com.jojo.design.common_base.view

import android.animation.ValueAnimator
import android.content.Context
import android.view.animation.LinearInterpolator
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import com.jojo.design.common_ui.R


/**
 *    author : JOJO
 *    e-mail : 18510829974@163.com
 *    date   : 2018/12/3 12:14 PM
 *    desc   : 仿IOS 菊花LoadingView
 */
class IOSLoadingView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) : View(context, attrs, defStyleAttr) {
    private var mWidth: Int = 0

    private var mHeight: Int = 0

    private var mCenterX: Int = 0

    private var mCenterY: Int = 0

    private var mPaint: Paint? = null

    private val mDefaultColor = -0x666667

    private val mDefaultSegmentWidth = 8

    private val mDefaultSegmentLength = 20

    private var mSegmentWidth = mDefaultSegmentWidth

    private var mSegmentColor = mDefaultColor

    private var mSegmentLength = mDefaultSegmentLength


    private var control = 1

    init {
        init(context, attrs)
    }

    private fun init(context: Context, attrs: AttributeSet?) {

        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.IOS_LoadingView)

        val indexCount = typedArray.getIndexCount()

        for (i in 0 until indexCount) {

            val attr = typedArray.getIndex(i)

            when (attr) {
                R.styleable.IOS_LoadingView_pathColor -> mSegmentColor = typedArray.getColor(attr, mDefaultColor)

                R.styleable.IOS_LoadingView_segmentLength -> mSegmentLength = typedArray.getDimensionPixelSize(attr, mDefaultSegmentLength)

                R.styleable.IOS_LoadingView_segmentWidth -> mSegmentWidth = typedArray.getDimensionPixelSize(attr, mDefaultSegmentWidth)
            }

        }

        typedArray.recycle()



        mPaint = Paint()

        mPaint!!.isAntiAlias = true

        mPaint!!.strokeCap = Paint.Cap.ROUND

        mPaint!!.style = Paint.Style.STROKE

        mPaint!!.color = mSegmentColor

        mPaint!!.strokeWidth = mSegmentWidth.toFloat()


    }


    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {

        super.onMeasure(widthMeasureSpec, heightMeasureSpec)

        mWidth = MeasureSpec.getSize(widthMeasureSpec)

        mHeight = MeasureSpec.getSize(heightMeasureSpec)

        mCenterX = mWidth / 2

        mCenterY = mHeight / 2

    }


    override fun onDraw(canvas: Canvas) {

        super.onDraw(canvas)

        for (i in 0..11) {

            mPaint!!.alpha = (i + 1 + control) % 12 * 255 / 12

            canvas.drawLine(mCenterX.toFloat(), (mCenterY - mSegmentLength).toFloat(), mCenterX.toFloat(), (mCenterY - mSegmentLength * 2).toFloat(), mPaint)

            canvas.rotate(30F, mCenterX.toFloat(), mCenterY.toFloat())

        }

    }


    override fun onFinishInflate() {

        super.onFinishInflate()

        val valueAnimator = ValueAnimator.ofInt(12, 1)

        valueAnimator.duration = 1000

        valueAnimator.interpolator = LinearInterpolator()

        valueAnimator.repeatCount = ValueAnimator.INFINITE

        valueAnimator.repeatMode = ValueAnimator.RESTART

        valueAnimator.addUpdateListener { animation ->
            control = animation.animatedValue as Int

            invalidate()
        }

        valueAnimator.start()

    }
}