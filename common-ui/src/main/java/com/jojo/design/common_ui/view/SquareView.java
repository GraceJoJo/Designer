package com.jojo.design.common_ui.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;

import androidx.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * author : JOJO
 * e-mail : 18510829974@163.com
 * date   : 2018/12/13 10:26 AM
 * desc   :
 */
public class SquareView extends View {
    private Paint mPaint;
    private Paint mPaintWhite;
    int rectWidth = 4;

    public SquareView(Context context) {
        this(context, null);
    }

    public SquareView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SquareView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.BLACK);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(rectWidth);

        mPaintWhite = new Paint();
        mPaintWhite.setAntiAlias(true);
        mPaintWhite.setColor(Color.WHITE);
        mPaintWhite.setStyle(Paint.Style.STROKE);
        mPaintWhite.setStrokeWidth(rectWidth);

    }

    private float[] mCurrentPosition = new float[2];
    final int lineWidth = 50; //白线的宽度
    @Override
    protected void onDraw(final Canvas canvas) {
        super.onDraw(canvas);
        //绘制圆角矩形
//        RectF rectF = new RectF(0 + 50 + rectWidth / 2, 0 + 50 + rectWidth / 2, 200, 100);
//        canvas.drawRoundRect(rectF, 200, 200, mPaint);

        Path path = new Path();//通过此对象绘制一个轨迹
        path.moveTo(200, 200);//起始点
        path.lineTo(600, 200);
        path.lineTo(600, 600);
        path.lineTo(200, 600);//终止点
        path.close();//形成闭环
        canvas.drawPath(path, mPaint);

        final Path pathWhite = new Path();//通过此对象绘制一个轨迹
        pathWhite.moveTo(rectWidth / 2 + 200, 200);//起始点
        pathWhite.lineTo(rectWidth / 2 + 200 + lineWidth, 200);
        pathWhite.close();
        canvas.drawPath(pathWhite, mPaintWhite);


    }
}
