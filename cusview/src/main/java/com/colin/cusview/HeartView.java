package com.colin.cusview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class HeartView extends View {

    private Paint mPaint;
    private int mWidth, mHeight;

    //正方形的长宽
    private final float rectWH = 360f;
    private final float circleR = rectWH / 4f;

    public HeartView(Context context) {
        super(context);
    }

    public HeartView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mPaint = new Paint();
        mPaint.setColor(Color.RED);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setStrokeWidth(2f);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//        drawHelpLines(canvas);
//        drawRectF(canvas);
        drawHeart(canvas);
    }

    private void drawHelpLines(Canvas canvas) {
        //画出辅助线
        mPaint.setColor(Color.BLACK);
        canvas.drawLine(0, mHeight / 2f, mWidth, mHeight / 2f, mPaint);
        canvas.drawLine(mWidth / 2f, 0, mWidth / 2f, mHeight, mPaint);
    }

    private void drawRectF(Canvas canvas) {
        mPaint.setStrokeWidth(5f);
        mPaint.setColor(Color.RED);
        float x = (mWidth - rectWH) / 2f;
        float y = (mHeight - rectWH) / 2f;
        RectF rectF = new RectF(x, y, x + rectWH, y + rectWH);
        canvas.drawRect(rectF, mPaint);
    }

    private void drawHeart(Canvas canvas) {
        mPaint.setColor(Color.RED);
        float leftLeft = (mWidth - rectWH) / 2f;
        float leftTop = mHeight / 2f - rectWH / 4f;
        RectF leftRF = new RectF(leftLeft, leftTop, mWidth / 2f, leftTop + circleR * 2);

        float rightLeft = mWidth / 2f;
        float rightRight = (mWidth + rectWH) / 2f;
        float rightTop = mHeight / 2f - rectWH / 4f;
        RectF rightRF = new RectF(rightLeft, rightTop, rightRight, rightTop + circleR * 2);

        Path path = new Path();
        path.arcTo(leftRF, -210f, 210, true);
        path.arcTo(rightRF, -180, 210f, false);
        path.lineTo(mWidth / 2f, (mHeight + rectWH) / 2f + rectWH / 8f);
        path.close();
        canvas.drawPath(path, mPaint);
    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}
