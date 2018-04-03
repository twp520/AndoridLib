package com.colin.andoridlib.widget;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by tianweiping on 2018/1/18.
 */

public class SampleItemDecoration extends RecyclerView.ItemDecoration {

    private int verSpace;

    public SampleItemDecoration(int verSpace) {
        this.verSpace = verSpace;

    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        outRect.top = verSpace;
    }
}
