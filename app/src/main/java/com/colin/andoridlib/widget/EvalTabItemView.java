package com.colin.andoridlib.widget;

import android.content.Context;
import android.support.annotation.DrawableRes;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.colin.andoridlib.R;


/**
 * Created by tianweiping on 2018/1/17.
 */

public class EvalTabItemView implements IEvalTabItem {
    private Context mContext;
    private ImageView mIcon;
    private View mView;

    //    private String mTvString;
    private @DrawableRes
    int selectIcon, unSelectIcon;
    private int[] mMapperPageIndex;


    public EvalTabItemView(Context mContext, @DrawableRes int selectIcon,
                           @DrawableRes int unSelectIcon) {
        this.mContext = mContext;
//        this.mTvString = mTvString;
        this.selectIcon = selectIcon;
        this.unSelectIcon = unSelectIcon;
    }

    @Override
    public View getItemView(int viewType) {
        mView = LayoutInflater.from(mContext).inflate(R.layout.widget_eval_tab,
                null, false);

        mIcon = (ImageView) mView.findViewById(R.id.eval_tab_item_icon);

        mIcon.setImageResource(unSelectIcon);
        return mView;
    }

    @Override
    public void unChecked() {
//        mText.setTextColor(ContextCompat.getColor(mContext, R.color.font_color_base));
        mIcon.setImageResource(unSelectIcon);
    }

    @Override
    public void checked() {
//        mText.setTextColor(ContextCompat.getColor(mContext, R.color.colorPrimary));
        mIcon.setImageResource(selectIcon);
    }

    @Override
    public void setEnableStatus(boolean enable) {
        mView.setEnabled(enable);
//        mText.setEnabled(enable);
        mIcon.setEnabled(enable);
    }

    public void setMapperPageIndex(int... mapperPageIndex) {
        this.mMapperPageIndex = mapperPageIndex;
    }

    @Override
    public boolean hasClick() {
        return true;
    }

    @Override
    public int getItemViewType() {
        return 0;
    }

    @Override
    public int[] getMapperPageIndex() {
        return mMapperPageIndex;
    }


}
