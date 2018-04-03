package com.colin.cusview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by tianweiping on 2018/1/4.
 */

public class CusPathView extends View {

    private Paint mPaint;
    private Path mPath;

    private String TAG = "twp";

    public CusPathView(Context context) {
        this(context, null);
        Log.e(TAG, "CusPathView: 构造方法1");
    }

    public CusPathView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mPaint = new Paint();
        mPath = new Path();
        Log.e(TAG, "CusPathView: 构造方法2");
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        Log.e(TAG, "CusPathView: onSizeChanged");
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Log.e(TAG, "CusPathView: onDraw");
        mPaint.setColor(Color.RED);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setAntiAlias(true);

        mPath.addCircle(200, 200, 50, Path.Direction.CW);
        mPath.addCircle(150, 200, 50, Path.Direction.CW);
        mPath.addRect(100, 150, 250, 250, Path.Direction.CW);

        mPath.lineTo(600, 600);
        mPath.lineTo(400, 600);
        mPath.rLineTo(-100, 100);

        mPath.quadTo(500, 800, 450, 750);

        canvas.drawPath(mPath, mPaint);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        Log.e(TAG, "CusPathView: onMeasure");
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        Log.e(TAG, "CusPathView: onLayout");
    }
}
