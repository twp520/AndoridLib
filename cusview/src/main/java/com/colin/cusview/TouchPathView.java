package com.colin.cusview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class TouchPathView extends View {

    private Paint mPaint;
    private Path mPath;
    private float mPreX, mPreY;

    public TouchPathView(Context context) {
        super(context);
    }

    public TouchPathView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.RED);
        mPaint.setStrokeWidth(3f);
        mPaint.setStyle(Paint.Style.STROKE);
        mPath = new Path();
    }


    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mPath.moveTo(event.getX(), event.getY());
                mPreX = event.getX();
                mPreY = event.getY();
                return true;
            case MotionEvent.ACTION_MOVE:
//                float endX = (event.getX() + mPreX) / 2f;
//                float endY = (event.getY() + mPreY) / 2f;
//                mPath.quadTo(mPreX, mPreY, endX, endY);
//                mPreX = event.getX();
//                mPreY = event.getY();
                mPath.lineTo(event.getX(),event.getY());
                invalidate();
                break;
            default:
                break;
        }
        return super.onTouchEvent(event);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawPath(mPath, mPaint);
    }

    public void reset() {
        mPath.reset();
    }
}
