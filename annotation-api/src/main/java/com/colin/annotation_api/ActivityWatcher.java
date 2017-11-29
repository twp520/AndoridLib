package com.colin.annotation_api;

import android.app.Activity;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by tianweiping on 2017/11/28.
 */

public class ActivityWatcher implements Watcher {
    private HashMap<View, ArrayList<EditText>> map = new HashMap<>();


    @Override
    public EditText findView(Object obj, int id) throws ClassCastException {
        return (EditText) ((Activity) obj).findViewById(id);
    }

    @Override
    public void watchEdit(EditText editText, final View obser) {
        if (map.get(obser) == null) {
            ArrayList<EditText> itemEditList = new ArrayList<>();
            itemEditList.add(editText);
            map.put(obser,itemEditList);
        } else {
           map.get(obser).add(editText);
        }
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (checkEnable(map.get(obser)))
                    obser.setEnabled(true);
                else obser.setEnabled(false);
            }
        });


    }

    private boolean checkEnable(ArrayList<EditText> editList) {
        for (EditText text : editList) {
            if (TextUtils.isEmpty(text.getText().toString()))
                return false;
        }
        return true;
    }
}
