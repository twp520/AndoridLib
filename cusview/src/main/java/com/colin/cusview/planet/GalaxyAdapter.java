package com.colin.cusview.planet;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

public abstract class GalaxyAdapter<T> {
    private OnDataSetChangeListener onDataSetChangeListener;

    public abstract int getCount();

    public abstract View getView(Context context, int position, ViewGroup parent);

    public abstract T getItem(int position);

    public abstract int getPopularity(int position);

    public abstract void onThemeColorChanged(View view, int themeColor, float alpha);

    public final void notifyDataSetChanged() {
        onDataSetChangeListener.onChange();
    }

    protected interface OnDataSetChangeListener {
        void onChange();
    }

    protected void setOnDataSetChangeListener(OnDataSetChangeListener listener) {
        onDataSetChangeListener = listener;
    }
}
