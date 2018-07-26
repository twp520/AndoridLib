package com.colin.cusview;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class WavePathView extends View {

    private int mWidth, mHeight;
    private Paint mPaint;
    private Path mPath;
    private Path mCosPath;
    private ValueAnimator sinAnim;

    public WavePathView(Context context) {
        super(context);
    }

    public WavePathView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(3f);
        mPaint.setColor(Color.RED);
        mPath = new Path();
        mPath.moveTo(0, 0);
        mCosPath = new Path();
        mCosPath.moveTo(0, 0);
        mPreX = 0;
        mPreY = 0;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//        drawHelpLines(canvas);
//        canvas.save();
        canvas.translate(0, mHeight / 2f);
        canvas.drawPath(mPath, mPaint);
        canvas.drawPath(mCosPath, mPaint);
//        canvas.restore();
    }

    private float mPreX, mPreY, mCosPreY;

    private void initAnimator() {
        sinAnim = ValueAnimator.ofFloat(0, mWidth);
        sinAnim.setDuration(1500);
        sinAnim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float x = (float) animation.getAnimatedValue();
                float sinY = (float) Math.sin(Math.PI * x / 180f) * 100f;
                float cosY = (float) Math.cos(Math.PI * x / 180f) * 100f;
                float endX = (x + mPreX) / 2f;
                float endY = (sinY + mPreY) / 2f;
                float endCosY = (cosY + mCosPreY) / 2f;
//                mPath.lineTo(x, y);
                mPath.quadTo(mPreX, mPreY, endX, endY);
                mCosPath.quadTo(mPreX, mCosPreY, endX, endCosY);
                postInvalidate();
                mPreX = x;
                mPreY = sinY;
                mCosPreY = cosY;
            }
        });

    }

    private void drawHelpLines(Canvas canvas) {
        //画出辅助线
        mPaint.setColor(Color.BLACK);
        canvas.drawLine(0, mHeight / 2f, mWidth, mHeight / 2f, mPaint);
        canvas.drawLine(mWidth / 2f, 0, mWidth / 2f, mHeight, mPaint);
    }

    public void startAnim() {
        if (null != sinAnim) {
            sinAnim.start();
        }
    }

    public void reset() {
        if (null != mPath) {
            mPath.reset();
            mPath.moveTo(0, 0);
        }
        if (null != mCosPath) {
            mCosPath.reset();
            mCosPath.moveTo(0, 0);
        }
        mPreY = 0;
        mPreX = 0;
        mCosPreY = 0;
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;
        initAnimator();
    }
}
