package com.colin.cusview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class PdXXView extends View {

    private Paint mPaint;
    private Bitmap dstBmp;
    private Bitmap srcBmp;
    private int width = 400, height = 400;
    private PorterDuffXfermode porterDuffXfermode;

    public PdXXView(Context context) {
        super(context);
    }

    public PdXXView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mPaint = new Paint();
        mPaint.setColor(Color.YELLOW);
        dstBmp = makDST();
        srcBmp = makSRC();
        porterDuffXfermode = new PorterDuffXfermode(PorterDuff.Mode.SRC_IN);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int saveLayer = canvas.saveLayer(null, null, Canvas.ALL_SAVE_FLAG);
        canvas.drawBitmap(dstBmp, 100, 100, mPaint);
        mPaint.setXfermode(porterDuffXfermode);
        canvas.drawBitmap(srcBmp, 200, 200, mPaint);
        canvas.restoreToCount(saveLayer);

    }

    private Bitmap makDST() {
        Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        RectF rectF = new RectF(0, 0, width, height);
        canvas.drawOval(rectF, mPaint);
        return bitmap;
    }

    private Bitmap makSRC() {
        Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        RectF rectF = new RectF(0, 0, width, height);
        mPaint.setColor(Color.BLUE);
        canvas.drawRect(rectF, mPaint);
        return bitmap;
    }
}
