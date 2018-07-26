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
import android.view.animation.LinearInterpolator;

public class QuadWavePathView extends View {

    private Paint mPaint;
    private Path mPath;

    //单位波长（一个波长是上半圆加下半圆）
    private int waveItemLength = 500;
    private int waveHalfItemLength = waveItemLength / 2;
    private float dx = 0;

    private int mWidth, mHeight;
    private String TAG = "qwpv";

    public QuadWavePathView(Context context) {
        super(context);
    }

    public QuadWavePathView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mPaint = new Paint();
        mPaint.setColor(Color.RED);
        mPaint.setStrokeWidth(3f);
        mPaint.setStyle(Paint.Style.STROKE);

        mPath = new Path();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int count = mWidth / waveItemLength;
        mPath.reset();
        mPath.moveTo(dx - waveItemLength, 400);
        for (int i = 0; i < count + 2; i++) {
            drawItemWave();
        }
        closePath();
        canvas.drawPath(mPath, mPaint);
    }

    private void drawItemWave() {
        //上半个波长
        int offsetY = 100;
        mPath.rQuadTo(waveHalfItemLength / 2f, -offsetY, waveHalfItemLength, 0);
        //下半个波长
        mPath.rQuadTo(waveHalfItemLength / 2f, offsetY, waveHalfItemLength, 0);

    }

    private void closePath() {
        mPaint.setStyle(Paint.Style.FILL);
        mPath.lineTo(mWidth, mHeight);
        mPath.lineTo(0, mHeight);
        mPath.close();
    }

    public void startWaveAnim() {
        ValueAnimator animator = ValueAnimator.ofFloat(0, waveItemLength);
        animator.setRepeatCount(ValueAnimator.INFINITE);
        animator.setDuration(1000);
        animator.setInterpolator(new LinearInterpolator());
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                dx = (float) animation.getAnimatedValue();
                postInvalidate();
            }
        });
        animator.start();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;
    }
}
