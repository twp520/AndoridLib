package com.colin.cusview.planet;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.colin.cusview.TypeValueUtil;

/**
 * create by colin
 * <p>
 * 星系view
 */
public class GalaxyView extends ViewGroup implements Runnable {
    private String TAG = "GalaxyView";
    private PlanetCloud mCloud;
    private float radiusPercent = 0.8f;
    private float radius;
    private float mCenterX, mCenterY;
    private GalaxyAdapter mAdapter;
    private Handler mHandler;

    public GalaxyView(Context context) {
        super(context);
        init();
    }

    public GalaxyView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public GalaxyView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mCloud = new PlanetCloud();
        mHandler = new Handler(Looper.getMainLooper());
    }

    public void setAdapter(GalaxyAdapter adapter) {
        mAdapter = adapter;
        initFromAdapter();
    }

    private void initFromAdapter() {
        mCloud.clear();
        mCenterX = getWidth() / 2f;
        mCenterY = getHeight() / 2f;
        radius = Math.min(mCenterX * radiusPercent, mCenterY * radiusPercent);
        mCloud.setCenterAndRadius(mCenterX, mCenterY, radius);
        for (int i = 0; i < mAdapter.getCount(); i++) {
            //初始化单个星球
            Planet planet = new Planet();
            planet.mView = mAdapter.getView(getContext(), i, this);
            planet.popularity = mAdapter.getPopularity(i);
            mCloud.add(planet);
            //TODO 添加点击事件
        }
        mCloud.create();
        resetChildren();
    }


    private void resetChildren() {
        removeAllViews();
        for (Planet planet : mCloud.getPlanets()) {
            addView(planet.mView);
        }
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        mHandler.post(this);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        for (int i = 0; i < getChildCount(); i++) {
            Planet planet = mCloud.get(i);
            View child = getChildAt(i);
            int left = (int) planet.loc2DX;
            int top = (int) planet.loc2DY;
            int right = left + child.getMeasuredWidth();
            int bottom = top + child.getMeasuredHeight();
            child.setScaleX(planet.scale);
            child.setScaleY(planet.scale);
            child.layout(left, top, right, bottom);
//            Log.e(TAG, "onLayout: child width = " + child.getWidth() + ".....child measuredWidth = " + child.getMeasuredWidth());
            Log.e(TAG, "child = " + child + "  onLayout:  x = " + left + ".......y = " + top + "....right = " + right + ".....bottom = " + bottom);
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = getMeasureSize((int) TypeValueUtil.getScreenWidth(getContext()), widthMeasureSpec);
        int height = getMeasureSize((int) TypeValueUtil.getScreenHeight(getContext()), heightMeasureSpec);
        setMeasuredDimension(width, height);
        measureChildren(MeasureSpec.UNSPECIFIED, MeasureSpec.UNSPECIFIED);
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

    @Override
    public void run() {
        //TODO 开启移动
    }
}
