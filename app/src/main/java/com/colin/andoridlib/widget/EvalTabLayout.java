package com.colin.andoridlib.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;



import java.util.List;

/**
 * Created by tianweiping on 2018/1/17.
 */

public class EvalTabLayout extends LinearLayout implements ViewPager.OnPageChangeListener {

    private List<IEvalTabItem> mItems;
    private LayoutParams itemLp;
    private int stepCount; //这个是上面的item个数
    private int stepTrueCount;//这个是viewpager里面页面的个数
    private int curTrueStep = 0;//这个是当前viewpager到达的index
    private int tabStep = 0;//tab当前在哪儿
    private IEvalTabItem mLastSelect;
    private ViewPager mViewPager;

    public EvalTabLayout(Context context) {
        super(context);
    }

    public EvalTabLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        init();
    }

    private void init() {
        setOrientation(HORIZONTAL);
        setPadding(ConvertUtils.dp2px(20), 0, ConvertUtils.dp2px(20), 0);
        itemLp = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT);
    }

    public void setItems(List<IEvalTabItem> items) {
        mItems = items;
        stepCount = mItems.size();
        for (int i = 0; i < stepCount; i++) {
            IEvalTabItem item = mItems.get(i);
            addView(item.getItemView(item.getItemViewType()), itemLp);
            item.unChecked();
            item.setEnableStatus(false);
        }
        mItems.get(0).checked();
        mItems.get(0).setEnableStatus(true);
        mLastSelect = mItems.get(0);
        tabStep = 0;
        for (int i = 0; i < getChildCount(); i++) {
            if (mItems.get(i).hasClick()) {
                View view = getChildAt(i);
                view.setOnClickListener(v -> {
                    int curIndex = indexOfChild(v);
                    mLastSelect.unChecked();
                    tabStep = curIndex;
                    mItems.get(curIndex).checked();
                    mLastSelect = mItems.get(curIndex);
                    if (mViewPager != null) {
                        mViewPager.setCurrentItem(mItems.get(curIndex).getMapperPageIndex()[0], true);
                    }
                });
            }
        }
    }

    public void stepWithViewPager(ViewPager viewPager) {
        mViewPager = viewPager;
        stepTrueCount = mViewPager.getAdapter().getCount();
        mViewPager.addOnPageChangeListener(this);
    }

    public int getTabStep() {
        return tabStep;
    }

    public int getCurTrueStep() {
        return curTrueStep;
    }

    @Override
    public void onPageSelected(int position) {
        curTrueStep = position;
//        KLog.e("EvalTabLayout ----->>>>>onPageSelected---->>>position= " + position);
        for (int i = 0; i < mItems.size(); i++) {
//            KLog.e("循环中=== i = " + i);
            int[] indexs = mItems.get(i).getMapperPageIndex();
//            KLog.e("拿到了 indexs = " + indexs.length);
            if (hasValue(indexs, position)) {
//                KLog.e("找到了 == i = " + i);
                mLastSelect.unChecked();
                mItems.get(i).checked();
                mItems.get(i).setEnableStatus(true);
                mLastSelect = mItems.get(i);
                tabStep = i;
                return;
            }
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    public boolean hasValue(int[] array, int value) {
        if (array == null || array.length == 0)
            return false;
        for (int i = 0; i < array.length; i++) {
            if (array[i] == value)
                return true;
        }
        return false;
    }
}
