package com.colin.andoridlib.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatEditText;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.colin.andoridlib.R;


/**
 * Created by tianweiping on 2018/1/23.
 */

public class TextEditView extends LinearLayout {

    private View mView;

    private AppCompatEditText mEdit;
    private TextView mTitle, mText;
    private String title, unit;

    public TextEditView(Context context) {
        super(context);
    }

    public TextEditView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.TextEditView);
        title = typedArray.getString(R.styleable.TextEditView_textEditTitle);
        unit = typedArray.getString(R.styleable.TextEditView_textEditUnit);
    }


    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        init();
    }

    private void init() {
        mView = LayoutInflater.from(getContext()).inflate(R.layout.widget_text_edit, null, false);
        LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        mEdit = (AppCompatEditText) mView.findViewById(R.id.widget_te_edit);
        mTitle = (TextView) mView.findViewById(R.id.widget_te_title);
        mTitle.setText(title);
        mText = (TextView) mView.findViewById(R.id.widget_te_tv);
        mText.setText(unit);
        addView(mView, params);
    }

    public String getEditText() {
        return mEdit.getText().toString();
    }
}
