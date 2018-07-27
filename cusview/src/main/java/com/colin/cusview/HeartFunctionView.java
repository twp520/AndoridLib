package com.colin.cusview;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.CornerPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.LinearInterpolator;

import java.util.ArrayList;
import java.util.List;

public class HeartFunctionView extends View {
    private Paint mPaint;
    private Path mPath;
    private CornerPathEffect cornerPathEffect;
    private float[] pts = {};
    private List<Float> ptsList;

    public HeartFunctionView(Context context) {
        super(context);
    }

    public HeartFunctionView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(3f);
        mPaint.setColor(Color.RED);
        mPath = new Path();
        cornerPathEffect = new CornerPathEffect(20);
        ptsList = new ArrayList<>();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.translate(getWidth() / 2f, getHeight() / 2f);
//        canvas.drawPath(mPath, mPaint);
        canvas.drawPoints(pts, mPaint);
    }

    public void startFunction() {
        ValueAnimator animator = ValueAnimator.ofFloat(-200, 400);
        animator.setDuration(5000);
        animator.setInterpolator(new LinearInterpolator());
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float x = (float) animation.getAnimatedValue();
                double y = heartArcFunction(x);
                Log.e("colin", "onAnimationUpdate:  x = " + x + ".....y = " + y);
                ptsList.add(x);
                ptsList.add((float) (y * 100));
                ptsList.add(x);
                ptsList.add((float) (-y * 100));
                pts = new float[ptsList.size()];
                for (int i = 0; i < pts.length; i++) {
                    pts[i] = ptsList.get(i);
                }
                postInvalidate();
            }
        });
        animator.start();
    }

    private double heartFunction(float x) {
        return Math.sqrt((5 * Math.pow(x, 2) - 128) / (6 * Math.abs(x) - 5));
    }

    private double heartArcFunction(float seta) {
        double x = Math.PI * seta / 180f;
        return ((Math.sin(x) * Math.pow(Math.abs(Math.cos(x)), 0.5)) / (Math.sin(x) + (7f / 5f))) - 2 * Math.sin(x) + 2;
    }
}
