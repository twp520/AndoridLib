package com.colin.cusview;

import android.animation.ArgbEvaluator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.SweepGradient;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.animation.DecelerateInterpolator;

import java.text.DecimalFormat;

/**
 * Created by tianweiping on 2017/10/27.
 */

public class YbbView extends View {
    private Paint mPaint; //画笔
    private int width; //view的宽
    private int height;//view的高
    private int[] arcColors = {0xFF46d6ff, 0xFF3F51B5};//渐变的颜色
    private int startArc = 150; //圆弧开始的角度
    private int sweepArc = 240;//圆弧的长度
    private int endArc; //圆弧结束的长度
    private float arcWidth = 20;//弧度的宽度
    private int pointLength = 1; //每一个刻度的弧度
    private int nullLength = 3; //刻度留白的弧度
    private int pointCount = sweepArc / (pointLength + nullLength);//刻度的个数
    private float drawEndArc = sweepArc + pointLength; //实际绘制的圆弧的长度
    private int numberText; //数字
    private int[] backColors = {0XFF4774B5, 0XFFFBAA19, 0XFFF05150,0XFFFFFFFF};//背景渐变的颜色
    private int backColor = backColors[0];
    private int animTime;
    private DecimalFormat format = new DecimalFormat("000,000");
    private float tipTextSize;
    private float numTextSize;
    private RectF arcRectF;
    private float pointWidth;
    private ValueAnimator va;
    private ValueAnimator arcVa;
    private ValueAnimator pointVa;
    private DisplayMetrics metrics;
    private ValueAnimator backColorAnim;

    public YbbView(Context context) {
        super(context);
        init();
    }

    public YbbView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.YbbView);
        metrics = getResources().getDisplayMetrics();
        arcWidth = typedArray.getDimension(R.styleable.YbbView_arcWidth, TypeValueUtil.dp2px(metrics, 8));
        pointWidth = typedArray.getDimension(R.styleable.YbbView_pointWidth, TypeValueUtil.dp2px(metrics, 5));
        pointLength = typedArray.getInteger(R.styleable.YbbView_pointArc, 1);
        nullLength = typedArray.getInteger(R.styleable.YbbView_pointSpacing, 3);
        numberText = typedArray.getInteger(R.styleable.YbbView_maxAmt, 500000);
        tipTextSize = typedArray.getDimension(R.styleable.YbbView_tipTextSize, TypeValueUtil.sp2px(metrics, 12));
        numTextSize = typedArray.getDimension(R.styleable.YbbView_amtTextSize, TypeValueUtil.sp2px(metrics, 30));
        animTime = typedArray.getInteger(R.styleable.YbbView_animTime, 1000);
        typedArray.recycle();
        init();

    }


    /**
     * 进行初始化
     */
    private void init() {
        mPaint = new Paint();
        mPaint.setColor(Color.WHITE);
        mPaint.setStyle(Paint.Style.FILL);
        endArc = startArc + sweepArc;
        pointCount = sweepArc / (pointLength + nullLength);//刻度的个数
        drawEndArc = sweepArc + pointLength; //实际绘制的圆弧的长度
        initAnim();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawBackground(canvas);
//        mPaint.setColor(Color.WHITE);
//        mPaint.setStrokeWidth(1);
//        canvas.drawLine(0, height / 2, width, height / 2, mPaint);
//        canvas.drawLine(width / 2, 0, width / 2, height, mPaint);
//        canvas.translate(0, height / 5);
//        canvas.save();
        drawArc(canvas);
        drawPoint(canvas);
//        canvas.restore();
        drawText(canvas);
        drawMoneyText(canvas);

    }

    private void initAnim() {
        va = ValueAnimator.ofFloat(numberText);
        va.setDuration(animTime);
        va.setInterpolator(new DecelerateInterpolator());
        va.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float currValue = (float) animation.getAnimatedValue();
                numberText = (int) currValue;
                invalidate();
            }
        });

        arcVa = ValueAnimator.ofFloat(drawEndArc);
        arcVa.setDuration(animTime);
        arcVa.setInterpolator(new DecelerateInterpolator());
        arcVa.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                drawEndArc = (float) animation.getAnimatedValue();
                invalidate();
            }
        });
        pointVa = ValueAnimator.ofInt(pointCount);
        pointVa.setDuration(animTime);
        pointVa.setInterpolator(new DecelerateInterpolator());
        pointVa.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                pointCount = (int) animation.getAnimatedValue();
                invalidate();
            }
        });

        backColorAnim = ValueAnimator.ofInt(backColors);
        backColorAnim.setDuration(animTime);
        backColorAnim.setEvaluator(new ArgbEvaluator());
        backColorAnim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                backColor = (int) animation.getAnimatedValue();
                invalidate();
            }
        });
    }

    public void startAnim() {
        va.start();
        arcVa.start();
        pointVa.start();
        backColorAnim.start();
    }

    public void stopAnim() {
        va.end();
        arcVa.end();
        pointVa.end();
        backColorAnim.end();
    }


    /**
     * 绘制数字
     *
     * @param canvas
     */
    private void drawMoneyText(Canvas canvas) {
        int top = (int) (height / 2 + TypeValueUtil.dp2px(metrics, 15));
        int left = width / 2;
        mPaint.setTextSize(numTextSize);
        String text = format.format(numberText);
        float textW = mPaint.measureText(text);
        LinearGradient lg = new LinearGradient(left, top, left + textW, top, arcColors, null, Shader.TileMode.CLAMP);
        mPaint.setShader(lg);
        canvas.drawText(text, left, top, mPaint);
    }

    /**
     * 绘制小文字
     */
    private void drawText(Canvas canvas) {
        int top = (int) (height / 3 + TypeValueUtil.dp2px(metrics, 15));
        int left = width / 2;
        mPaint.setTextSize(tipTextSize);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setTextAlign(Paint.Align.CENTER);
        String text = "涂山苏苏多少岁？";
        float textW = mPaint.measureText(text);
        LinearGradient lg = new LinearGradient(left, top, left + textW, top, arcColors, null, Shader.TileMode.CLAMP);
        mPaint.setShader(lg);
        canvas.drawText(text, left, top, mPaint);
    }

    /**
     * 绘制刻度
     *
     * @param canvas
     */
    private void drawPoint(Canvas canvas) {
        float d = Math.min(width, height);
        float f = (int) TypeValueUtil.dp2px(metrics, 18);
        float r = d / 2 - f;
        float left = d / 2 - r;
        float right = d / 2 + r;
        RectF rectF = new RectF(left, f, right, d - f);
        mPaint.setStrokeWidth(pointWidth);
        int startArc = this.startArc;
        for (int i = 0; i <= pointCount; i++) {
            canvas.drawArc(rectF, startArc, pointLength, false, mPaint);
            startArc = startArc + nullLength + pointLength;
        }
    }

    /**
     * 绘制外部的弧线
     *
     * @param canvas
     */
    private void drawArc(Canvas canvas) {
        int d = Math.min(width, height);
        float f = TypeValueUtil.dp2px(metrics, 7); // 这个弧线距离view四周的margin
        float r = d / 2 - f;
        float left = d / 2 - r;
        float right = d / 2 + r;
        arcRectF = new RectF(left, f, right, d - f);
        mPaint.setStrokeWidth(arcWidth);
        mPaint.setAntiAlias(true);//设置无锯齿
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setShader(getGradient(d, arcColors, startArc));
        canvas.drawArc(arcRectF, startArc, drawEndArc, false, mPaint);
    }

    /**
     * 得到一个渐变的圆弧叠加层
     *
     * @param d        圆的直径
     * @param colors   渐变数组
     * @param startArc 从什么角度开始渐变
     * @return
     */
    private SweepGradient getGradient(int d, int[] colors, int startArc) {
        SweepGradient sweepGradient = new SweepGradient(d / 2.0f, d / 2.0f, colors, null);
        //旋转 不然是从0度开始渐变
        Matrix matrix = new Matrix();
        matrix.setRotate(startArc, d / 2.0f, d / 2.0f);
        sweepGradient.setLocalMatrix(matrix);
        return sweepGradient;
    }


    /**
     * 绘制view的背景
     */
    private void drawBackground(Canvas canvas) {
        canvas.drawColor(backColor);
//        canvas.drawBitmap();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        width = w;
        height = (int) (h / 0.8f);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = getMeasureSize(100, widthMeasureSpec);
        int height = getMeasureSize(100, heightMeasureSpec);
        setMeasuredDimension(width, (int) (height * 0.8f));
    }


    private int getMeasureSize(int def, int measureSpec) {
        int measuresize = 0;
        int mode = MeasureSpec.getMode(measureSpec);
        int size = MeasureSpec.getSize(measureSpec);
        if (mode == MeasureSpec.UNSPECIFIED) {
            measuresize = def;
        } else if (mode == MeasureSpec.EXACTLY) {
            measuresize = size;
        } else if (mode == MeasureSpec.AT_MOST) {
            measuresize = Math.min(def, size);
        }
        return measuresize;
    }

}
