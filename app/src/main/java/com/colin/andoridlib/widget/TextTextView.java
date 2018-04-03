package com.colin.andoridlib.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.colin.andoridlib.R;


/**
 * Created by tianweiping on 2018/1/23.
 */

public class TextTextView extends LinearLayout {

    private String mTitle, mSubTitle;
    private View mView;
    private TextView mTitleView, mSubTitleView;
    private ImageView mIcon;
    private boolean hideIcon = false;
    private int titleColor, subTitleColor;

    public TextTextView(Context context) {
        super(context);
    }

    public TextTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.TextTextView);
        mTitle = typedArray.getString(R.styleable.TextTextView_textTextTitle);
        mSubTitle = typedArray.getString(R.styleable.TextTextView_textTextSubTitle);
        hideIcon = typedArray.getBoolean(R.styleable.TextTextView_hideIconRight, false);
        titleColor = typedArray.getColor(R.styleable.TextTextView_titleColor, ContextCompat.getColor(getContext(), R.color.font_color_base));
        subTitleColor = typedArray.getColor(R.styleable.TextTextView_subTitleColor, ContextCompat.getColor(getContext(), R.color.font_color_secondary));
        typedArray.recycle();
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        init();
    }

    private void init() {
        mView = LayoutInflater.from(getContext()).inflate(R.layout.widget_text_text, null, false);
        LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        mTitleView = (TextView) mView.findViewById(R.id.widget_tt_title);
        mSubTitleView = (TextView) mView.findViewById(R.id.widget_tt_tv);
        mIcon = (ImageView) mView.findViewById(R.id.widget_tt_icon);
        mTitleView.setText(mTitle);
        mTitleView.setTextColor(titleColor);
        mSubTitleView.setText(mSubTitle);
        mSubTitleView.setTextColor(subTitleColor);
        if (hideIcon)
            hideIcon();
        addView(mView, params);
    }

    public String getSubTitle() {
        return mSubTitleView.getText().toString();
    }

    public void setSubTitle(String subTitle) {
        mSubTitleView.setText(subTitle);
    }

    public void hideIcon() {
        mIcon.setVisibility(GONE);
    }
}
