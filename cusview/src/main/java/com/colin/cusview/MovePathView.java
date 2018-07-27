package com.colin.cusview;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;

public class MovePathView extends View {

    private Paint mPaint;
    private Path mPath;
    private DashPathEffect dashPathEffect;

    public MovePathView(Context context) {
        super(context);
    }

    public MovePathView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(3f);
        mPaint.setColor(Color.RED);

        mPath = new Path();
        dashPathEffect = new DashPathEffect(new float[]{30f, 10f, 15f, 30f}, 0);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPath.reset();
        mPath.moveTo(200, 200);
        mPath.rLineTo(0, 200);
        mPath.rLineTo(100, 0);
        mPath.rLineTo(50, -300);
        mPath.lineTo(220, 180);

        mPaint.setPathEffect(dashPathEffect);
        canvas.drawPath(mPath, mPaint);


    }

    public void startMove() {
        ValueAnimator animator = ValueAnimator.ofInt(0, 85);
        animator.setDuration(1000);
        animator.setInterpolator(new LinearInterpolator());
        animator.setRepeatCount(ValueAnimator.INFINITE);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int value = (int) animation.getAnimatedValue();
                dashPathEffect = new DashPathEffect(new float[]{30f, 10f, 15f, 30f}, value);
                postInvalidate();
            }
        });
        animator.start();
    }

}
