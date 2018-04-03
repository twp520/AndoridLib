package com.colin.andoridlib.widget;

import android.content.Context;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tianweiping on 2018/1/23.
 * 动态添加一组布局的list
 * <p>
 * T 最后转换的数据集的item的数据类型
 * <p>
 * V viewholder
 */

public abstract class SimpleDataAdapter<T, V extends SimpleDataAdapter.SimpleViewHolder> {

    private List<T> mData;
    private int mCount;
    protected Context mContext;

    public SimpleDataAdapter(Context context) {
        mContext = context;
        mData = new ArrayList<>();
        mCount = getInitCount();
    }

    /**
     * 获得一个item的view
     *
     * @return view
     */
    public abstract View getView();

    public abstract T getFinalData(V holder);

    public abstract V createViewHolder(View itemView);

    public abstract int getInitCount();

    protected List<T> getFinalDataList() {
        mData.clear();
        for (int i = 0; i < getCount(); i++) {
            mData.add(getFinalData((V) getView().getTag()));
        }
        return mData;
    }

    public static class SimpleViewHolder {
        private View itemView;

        public SimpleViewHolder(View itemView) {
            this.itemView = itemView;
        }
    }

    public void countAdd() {
        mCount++;
    }

    private int getCount() {
        return mCount;
    }

    private void clear() {
        mData.clear();
        mCount = 0;
    }
}
