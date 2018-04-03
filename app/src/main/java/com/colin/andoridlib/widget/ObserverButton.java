package com.colin.andoridlib.widget;

import android.content.Context;
import android.support.v7.widget.AppCompatButton;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

/**
 * 监听按钮
 * Created by yyz on 2018/1/17.
 */

public class ObserverButton extends AppCompatButton implements TextWatcher {

    private ArrayList<EditText> editTexts;
    private HashMap<String, Boolean> kvMap;

    public ObserverButton(Context context) {
        this(context, null);
        init();
    }

    public ObserverButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ObserverButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        editTexts = new ArrayList<>();
        kvMap = new HashMap<>();
    }


    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        setEnabled(checkEmpty());
    }

    private boolean checkEmpty() {
        boolean isRight = true;
        for (EditText et : editTexts) {
            if (TextUtils.isEmpty(et.getText().toString().trim())) {
                isRight = false;
                break;
            }

        }
        return isRight;
    }

    public void observerEnable(EditText... ets) {
        for (EditText et : ets) {
            et.addTextChangedListener(this);
            editTexts.add(et);
        }
    }

    public void observerEnable(HashMap<String, Boolean> kv) {
        kvMap.clear();
        kvMap.putAll(kv);
    }

    public void changeEnable(String key, boolean value) {
        kvMap.put(key, value);
        if (kvCheck())
            setEnabled(true);
        else setEnabled(false);
    }

    private boolean kvCheck() {
        Collection<Boolean> values = kvMap.values();
        for (Boolean ab : values) {
            if (!ab) {
                return false;
            }
        }
        return true;
    }

    public void unObserver() {
        editTexts.clear();
        kvMap.clear();
    }
}
