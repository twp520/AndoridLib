package com.colin.andoridlib.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.support.v7.widget.SwitchCompat;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.colin.andoridlib.R;


/**
 * Created by tianweiping on 2018/1/22.
 */

public class TextSwitchView extends LinearLayout {

    private SwitchCompat mSwitch;
    private ImageView mIcon;
    private TextView mTitle;
    private String mTitleText;

    public TextSwitchView(Context context) {
        super(context);
    }

    public TextSwitchView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.TextSwitchView);
        mTitleText = typedArray.getString(R.styleable.TextSwitchView_textSwitchTitle);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        init();
    }

    private void init() {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.widget_text_switch, null, false);
        LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        mSwitch = (SwitchCompat) view.findViewById(R.id.widget_tsw_switch);
        mIcon = (ImageView) view.findViewById(R.id.widget_tsw_icon);
        mTitle = (TextView) view.findViewById(R.id.widget_tsw_title);
        mTitle.setText(mTitleText);
        addView(view, params);
    }

    public void setOnCheckedChangeListener(CompoundButton.OnCheckedChangeListener listener) {
        mSwitch.setOnCheckedChangeListener(listener);
    }
}
