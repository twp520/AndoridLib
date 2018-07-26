package com.colin.andoridlib.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;

import com.colin.andoridlib.R;


/**
 * Created by tianweiping on 2018/1/25.
 */

public class MyListIndexBar extends View {

    private String[] indexs = {
            "A", "B", "C", "D", "E", "F",
            "G", "H", "I", "J", "K", "L",
            "M", "N", "O", "P", "Q", "R",
            "S", "T", "U", "V", "W", "X",
            "Y", "Z"};

    private Paint mPaint;
    private float mWidth, mHeight, mCellWidth, mCellHeight;
    private OnLetterChangeListener mListener;
    private int lastIndex = -1;

    public MyListIndexBar(Context context) {
        super(context);
    }

    public MyListIndexBar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.STROKE);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //绘制背景颜色
        canvas.drawColor(Color.TRANSPARENT);
        //绘制文字
        mPaint.setTextSize(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,
                10f, getResources().getDisplayMetrics()));
//        mPaint.setColor(ContextCompat.getColor(getContext(), R.color.font_color_secondary));
        for (int i = 0; i < indexs.length; i++) {
            String text = indexs[i];
            int x = (int) (mCellWidth / 2f - mPaint.measureText(text) / 2f);
            Rect bound = new Rect();
            mPaint.getTextBounds(text, 0, text.length(), bound);
            int y = (int) (mCellHeight / 2f + bound.height() / 2f + mCellHeight * i);
            canvas.drawText(text, x, y, mPaint);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                float yd = event.getY();
                int indexd = (int) (yd / mCellHeight);
                if (indexd != lastIndex) {
                    if (indexd >= 0 && indexd < indexs.length) {
                        if (mListener != null)
                            mListener.OnLetterChange(indexs[indexd]);
                    }
                    lastIndex = indexd;
                }
                break;
            case MotionEvent.ACTION_MOVE:
                float ym = event.getY();
                int indexm = (int) (ym / mCellHeight);
                if (indexm != lastIndex) {
                    if (indexm >= 0 && indexm < indexs.length) {
                        if (mListener != null)
                            mListener.OnLetterChange(indexs[indexm]);
                    }
                    lastIndex = indexm;
                }
                break;
            case MotionEvent.ACTION_UP:
                lastIndex = -1;
                break;
        }
        return true;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = getMeasuredWidth();
        mHeight = getMeasuredHeight();

        mCellHeight = mHeight / 1.0f / indexs.length;
        mCellWidth = mWidth;
    }

    public interface OnLetterChangeListener {
        void OnLetterChange(String letter);
    }

    public void setOnLetterChangeListener(OnLetterChangeListener mListener) {
        this.mListener = mListener;
    }
}
