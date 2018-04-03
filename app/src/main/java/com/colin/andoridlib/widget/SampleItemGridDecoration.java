package com.colin.andoridlib.widget;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by tianweiping on 2018/1/18.
 */

public class SampleItemGridDecoration extends RecyclerView.ItemDecoration {

    private int verSpace, horSpace;

    public SampleItemGridDecoration(int verSpace, int horSpace) {
        this.verSpace = verSpace;
        this.horSpace = horSpace;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        outRect.top = verSpace;
        outRect.right = horSpace;
    }
}
