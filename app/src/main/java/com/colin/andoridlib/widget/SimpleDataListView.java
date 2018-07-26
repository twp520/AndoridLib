package com.colin.andoridlib.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.colin.andoridlib.R;


/**
 * Created by tianweiping on 2018/1/23.
 */

public class SimpleDataListView extends LinearLayout {

    private LayoutParams childParams;

    private SimpleDataAdapter mAdapter;

    private boolean isShow = false;

    public SimpleDataListView(Context context) {
        super(context);
    }

    public SimpleDataListView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        init();
    }

    private void init() {
        setOrientation(VERTICAL);
        childParams = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        setShowDividers(SHOW_DIVIDER_MIDDLE);
//        setDividerDrawable(ContextCompat.getDrawable(getContext(), R.drawable.shape_linear_line));
    }

    public void setAdapter(SimpleDataAdapter adapter) {
        this.mAdapter = adapter;
        int count = mAdapter.getInitCount();
        for (int i = 0; i < count; i++) {
            View itemView = mAdapter.getView();
            itemView.setTag(mAdapter.createViewHolder(itemView));
            addView(itemView, childParams);
        }
        //添加脚view
//        View footer = LayoutInflater.from(getContext()).inflate(R.layout.simple_datalist_footer,
//                null, false);
//        footer.setOnClickListener(v -> addOneItemView());
//        addView(footer, new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
    }

    public void addOneItemView() {
        addView(mAdapter.getView(), getChildCount() - 1, childParams);
        mAdapter.countAdd();
    }

    public void clear() {
        removeAllViews();
        mAdapter.countAdd();
    }

    public void bindSwitchChecked(boolean isChecked) {
        isShow = isChecked;
        int vis = isChecked ? View.VISIBLE : View.GONE;
        setVisibility(vis);
    }

    public boolean isShow() {
        return isShow;
    }
}
