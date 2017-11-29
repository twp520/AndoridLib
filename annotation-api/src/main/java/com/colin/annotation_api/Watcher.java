package com.colin.annotation_api;

import android.view.View;
import android.widget.EditText;

/**
 * Created by tianweiping on 2017/11/28.
 */

public interface Watcher {
    /**
     * 查找view的方法
     *
     * @param obj view的来源，哪个activity或者fragment
     * @param id  要查找的view的id
     * @return 查找到的view
     */
    EditText findView(Object obj, int id) throws ClassCastException;

    /**
     * 进行观察
     *
     * @param editText 被观察的edit
     * @param obser    观察的view
     */
    void watchEdit(EditText editText, View obser);
}
