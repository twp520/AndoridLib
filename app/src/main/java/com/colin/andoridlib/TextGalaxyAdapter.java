package com.colin.andoridlib;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.colin.cusview.planet.GalaxyAdapter;

import java.util.ArrayList;

public class TextGalaxyAdapter extends GalaxyAdapter<String> {

    private ArrayList<String> mData;
    private String TAG="TextGalaxyAdapter";

    public TextGalaxyAdapter(ArrayList<String> mData) {
        this.mData = mData;
    }

    @Override
    public int getCount() {
        return mData == null ? 0 : mData.size();
    }

    @Override
    public View getView(Context context, int position, ViewGroup parent) {
        TextView textView = new TextView(context);
//        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
//        textView.setLayoutParams(params);
        textView.setTextColor(Color.WHITE);
        textView.setBackgroundColor(Color.GREEN);
        Log.e(TAG, "getView:  text = "+ mData.get(position));
        textView.setText(mData.get(position));
        return textView;
    }

    @Override
    public String getItem(int position) {
        return mData == null ? "" : mData.get(position);
    }

    @Override
    public int getPopularity(int position) {
        return position % 7;
    }

    @Override
    public void onThemeColorChanged(View view, int themeColor, float alpha) {

    }
}
