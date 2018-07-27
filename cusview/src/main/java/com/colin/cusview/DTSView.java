package com.colin.cusview;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;

public class DTSView extends View {

    private Bitmap bmpSrc, bmpDts;
    private Paint mPaint;
    private int itemWaveLength = 300;
    private Path mPath;
    private float waveHalfItemLength = itemWaveLength / 2f;
    private PorterDuffXfermode porterDuffXfermode;

    public DTSView(Context context) {
        super(context);
    }

    public DTSView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mPaint = new Paint();
        mPaint.setColor(Color.YELLOW);
        mPath = new Path();
        bmpSrc = makSRC();
        bmpDts = makDST(bmpSrc.getWidth(), bmpSrc.getHeight());
        porterDuffXfermode = new PorterDuffXfermode(PorterDuff.Mode.DST_IN);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawBitmap(bmpSrc, 0, 0, mPaint);
        makPath();
        int saveLayer = canvas.saveLayer(null, null, Canvas.ALL_SAVE_FLAG);
        canvas.drawBitmap(bmpDts, 0, 0, mPaint);
        mPaint.setXfermode(porterDuffXfermode);
        canvas.drawBitmap(bmpSrc, 0, 0, mPaint);
        mPaint.setXfermode(null);
        canvas.restoreToCount(saveLayer);
    }

    private void makPath() {
        Canvas canvas = new Canvas(bmpDts);
        //画水波纹
        canvas.drawColor(Color.YELLOW, PorterDuff.Mode.CLEAR);
        int count = bmpDts.getWidth() / itemWaveLength;
        mPath.reset();
        mPath.moveTo(dx - itemWaveLength, bmpDts.getHeight() / 2 + (dx % 2 == 0 ? -100 : 50));
        for (int i = 0; i < count + 2; i++) {
            drawItemWave();
        }
        closePath(bmpDts.getWidth(), bmpDts.getHeight());
        canvas.drawPath(mPath, mPaint);
    }

    private float dx;

    public void startAnim() {
        ValueAnimator animator = ValueAnimator.ofFloat(0, itemWaveLength);
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

    private Bitmap makDST(int width, int height) {
        return Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
    }

    private void drawItemWave() {
        //上半个波长
        int offsetY = 100;
        mPath.rQuadTo(waveHalfItemLength / 2f, -offsetY, waveHalfItemLength, 0);
        //下半个波长
        mPath.rQuadTo(waveHalfItemLength / 2f, offsetY, waveHalfItemLength, 0);

    }

    private void closePath(int width, int height) {
        mPaint.setStyle(Paint.Style.FILL);
        mPath.lineTo(width, height);
        mPath.lineTo(0, height);
        mPath.close();
    }

    private Bitmap makSRC() {
        return BitmapFactory.decodeResource(getResources(), R.drawable.testtext);
    }
}
