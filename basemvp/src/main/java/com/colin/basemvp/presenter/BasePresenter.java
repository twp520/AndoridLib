package com.colin.basemvp.presenter;

import android.content.Context;

/**
 * Created by tianweiping on 2017/12/11.
 */

public interface BasePresenter {

    /**
     * 与view绑定
     *
     * @param context
     */
    void onAttach(Context context);

    /**
     * 销毁
     */
    void onDestroy();
}
