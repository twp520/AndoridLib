package com.colin.cusview;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;

import java.util.Objects;

/**
 * create by colin
 */
public class GuideView extends View {

    private View targetView;
    private Paint mPaint;
    private int[] targetViewOut = new int[2];
    private int bgColor;
    private Bitmap arrow;
    private int targetViewWidth;
    private int targetViewHeight;
    private PorterDuffXfermode porterDuffXfermode;
    private StaticLayout staticLayout;
    private Activity activity;
    private int textHeight = 0;
    private float screenHeight, screenWidth;
    public static final int RECT = 1, CIRCLE = 2;
    private int highLightType = CIRCLE;
    private float arrowMargin = 100;

    public GuideView(Context context) {
        super(context);
        init(context, null);
    }

    public GuideView(Context context, Builder builder) {
        super(context);
        init(context, builder);
    }

    private void init(Context context, @Nullable Builder builder) {
        if (builder == null || builder.arrow == null)
            arrow = BitmapFactory.decodeResource(getResources(), R.drawable.guide_arrow);
        else arrow = builder.arrow;

        String guideText;
        if (builder == null || TextUtils.isEmpty(builder.guideText))
            guideText = context.getString(R.string.guide_text);
        else guideText = builder.guideText;

        float textSize;
        if (builder == null || builder.textSize == 0)
            textSize = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 17, context.getResources().getDisplayMetrics());
        else
            textSize = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, builder.textSize, context.getResources().getDisplayMetrics());

        if (builder == null || builder.bgColor == 0)
            bgColor = 0x98000000;
        else bgColor = builder.bgColor;

        if (builder != null && builder.lightShape != 0)
            this.highLightType = builder.lightShape;

        if (builder != null && builder.compMargin != 0)
            this.arrowMargin = builder.compMargin;
        screenWidth = getScreenWidth();
        screenHeight = getScreenHeight();
        porterDuffXfermode = new PorterDuffXfermode(PorterDuff.Mode.CLEAR);
        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.FILL);
        TextPaint textPaint = new TextPaint();
        textPaint.setColor(Color.WHITE);
        textPaint.setTextSize(textSize);
        textPaint.setTextAlign(Paint.Align.CENTER);
        staticLayout = new StaticLayout(guideText, textPaint, (int) screenWidth, Layout.Alignment.ALIGN_NORMAL,
                1.5f, 0, true);
        int lineCount = staticLayout.getLineCount();
        Rect rect = new Rect();

        for (int i = 0; i < lineCount; i++) {
            staticLayout.getLineBounds(i, rect);
            textHeight += rect.height();
        }
    }

    public void setTargetView(View targetView) {
        this.targetView = targetView;
        targetView.getLocationOnScreen(targetViewOut);
        targetViewWidth = targetView.getWidth();
        targetViewHeight = targetView.getHeight();
        invalidate();
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //设置背景颜色为透明
        setBackgroundColor(Color.TRANSPARENT);
        if (targetView != null) {
            int bitmapW = arrow.getWidth();
            int bitmapH = arrow.getHeight();
            int saved = canvas.saveLayer(null, null, Canvas.ALL_SAVE_FLAG);
            //绘制半透明的底色
            canvas.drawColor(bgColor);
            //根据targetView位置，判断在屏幕的方位 绘制箭头
            float arrowLeft, arrowTop, textTranslateY;
            if (targetViewOut[1] > screenHeight / 2) { //屏幕下半区
                arrowLeft = targetViewOut[0] - bitmapW;
                arrowTop = targetViewOut[1] - bitmapH - arrowMargin;
                textTranslateY = arrowTop - textHeight - arrowMargin;
            } else {//屏幕上半区
                arrowLeft = targetViewOut[0] + targetViewWidth;
                arrowTop = targetViewOut[1] + targetViewHeight + arrowMargin;
                textTranslateY = arrowTop + bitmapH + arrowMargin;
            }
            canvas.drawBitmap(arrow, arrowLeft, arrowTop, mPaint);

            //绘制引导文字
            canvas.save();
//            canvas.translate(screenWidth / 2 - maxLineWidth / 2, screenHeight / 2);
            canvas.translate(screenWidth / 2, textTranslateY);
            staticLayout.draw(canvas);
            canvas.restore();

            //绘制高亮区域
            mPaint.setXfermode(porterDuffXfermode);
            if (highLightType == CIRCLE) {
                int cx = targetViewOut[0] + targetViewWidth / 2;
                int cy = targetViewOut[1] + targetViewHeight / 2;
                canvas.drawCircle(cx, cy, Math.max(targetViewWidth, targetViewHeight) / 2, mPaint);
            } else if (highLightType == RECT) {
                canvas.drawRect(targetViewOut[0], targetViewOut[1], targetViewOut[0] + targetViewWidth,
                        targetViewOut[1] + targetViewHeight, mPaint);
            }
            mPaint.setXfermode(null);
            canvas.restoreToCount(saved);
        }
    }

    public void addToActivity(Activity activity) {
        this.activity = activity;
        FrameLayout dec = (FrameLayout) activity.getWindow().getDecorView();
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT);
        dec.addView(this, params);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        performClick();
        FrameLayout dec = (FrameLayout) activity.getWindow().getDecorView();
        dec.removeView(this);
        return true;
    }

    @Override
    public boolean performClick() {
        return super.performClick();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = getMeasureSize(100, widthMeasureSpec);
        int height = getMeasureSize(101, heightMeasureSpec);
        setMeasuredDimension(width, height);
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

    private float getScreenWidth() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((WindowManager) Objects.requireNonNull(getContext().getApplicationContext().getSystemService(Context.WINDOW_SERVICE)))
                .getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.widthPixels;
    }

    private float getScreenHeight() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((WindowManager) Objects.requireNonNull(getContext().getApplicationContext().getSystemService(Context.WINDOW_SERVICE)))
                .getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.heightPixels;
    }


    public static class Builder {
        private float textSize; //字体大小
        private int bgColor;
        private String guideText;
        private View targetView;
        private Bitmap arrow;
        private int lightShape;
        private int compMargin;

        /**
         * set the guide text size
         *
         * @param textSize textSize sp
         * @return builder
         */
        public Builder textSize(float textSize) {
            this.textSize = textSize;
            return this;
        }

        /**
         * set the background color
         *
         * @param bgColor color like 0x33333333
         * @return builder
         */
        public Builder backgroundColor(int bgColor) {
            this.bgColor = bgColor;
            return this;
        }

        /**
         * set the guideText
         *
         * @param guideText some guide text
         * @return builder
         */
        public Builder guideText(String guideText) {
            this.guideText = guideText;
            return this;
        }

        /**
         * set the target view
         *
         * @param targetView a view need guide
         * @return builder
         */
        public Builder targetView(View targetView) {
            this.targetView = targetView;
            return this;
        }

        /**
         * set the arrow bitmap
         *
         * @param arrow bitmap for arrow
         * @return builder
         */
        public Builder arrowBitmap(Bitmap arrow) {
            this.arrow = arrow;
            return this;
        }

        /**
         * set the highlight area type
         *
         * @param type bitmap for arrow
         * @return builder
         */
        public Builder lightShapeType(int type) {
            this.lightShape = type;
            return this;
        }

        public Builder compMargin(int margin) {
            this.compMargin = margin;
            return this;
        }

        /**
         * build the GuideView
         *
         * @param context the view need context
         * @return GuideView
         */
        public GuideView build(Context context) {
            GuideView guideView = new GuideView(context, this);
            guideView.setTargetView(this.targetView);
            return guideView;
        }
    }
}
