package com.colin.andoridlib.widget;

import android.view.View;

/**
 * Created by tianweiping on 2018/1/17.
 */

public interface IEvalTabItem {

    View getItemView(int viewType);

    void unChecked();

    void checked();

    void setEnableStatus(boolean enable);

    boolean hasClick();

    int getItemViewType();

    int[] getMapperPageIndex();

}
