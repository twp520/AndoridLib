package com.colin.andoridlib.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatSpinner;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.colin.andoridlib.R;

import java.util.List;

/**
 * Created by tianweiping on 2018/1/22.
 */

public class TextSpinnerView extends LinearLayout {
    private AppCompatSpinner mSp;
    private TextView mTitle;
    private ImageView mIcon;

    private String mTitleText;
    private boolean hideIcon;

    public TextSpinnerView(Context context) {
        this(context, null);

    }

    public TextSpinnerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.TextSpinnerView);
        mTitleText = typedArray.getString(R.styleable.TextSpinnerView_textSpinnerTitle);
        hideIcon = typedArray.getBoolean(R.styleable.TextSpinnerView_hideIcon, false);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        init();
    }

    private void init() {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.widget_text_spinner, null, false);
        LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        mSp = (AppCompatSpinner) view.findViewById(R.id.widget_ts_sp);
        mTitle = (TextView) view.findViewById(R.id.widget_ts_title);
        mIcon = (ImageView) view.findViewById(R.id.widget_ts_icon);
        mTitle.setText(mTitleText);
        if (hideIcon)
            mIcon.setVisibility(INVISIBLE);
        addView(view, params);
    }

    public String getSelectItem() {
        return mSp.getSelectedItem().toString();
    }

    public void setSpinnerData(List<CharSequence> data) {
//        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(),
//                data, R.layout.widget_text_spinner_spitem);
        ArrayAdapter<CharSequence> adapter = new ArrayAdapter<>(getContext(), R.layout.widget_text_spinner_spitem, data);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSp.setAdapter(adapter);
    }

    public void setSpinnerData(CharSequence[] data) {
//        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(),
//                data, R.layout.widget_text_spinner_spitem);
        ArrayAdapter<CharSequence> adapter = new ArrayAdapter<>(getContext(), R.layout.widget_text_spinner_spitem, data);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSp.setAdapter(adapter);
    }

    public void setSpinnerSelectListener(AdapterView.OnItemSelectedListener itemSelectedListener) {
        mSp.setOnItemSelectedListener(itemSelectedListener);
    }

}
