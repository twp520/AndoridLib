package com.colin.andoridlib.widget;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.xiaoan.times.channel.loan.R;

/**
 * Created by tianweiping on 2018/1/25.
 */

public class CityPopWindow extends PopupWindow {

    private TextView mCityTv;
    private RecyclerView mRecy;


    public CityPopWindow(Context context) {
        super(context);
        init(context);
    }

    private void init(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.pop_city_list_layout,
                null, false);
        setContentView(view);
    }
}
