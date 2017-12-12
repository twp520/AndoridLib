package com.colin.cusview;

import android.util.DisplayMetrics;
import android.util.TypedValue;

/**
 * Created by tianweiping on 2017/12/11.
 */

public class TypeValueUtil {

    public static float dp2px(DisplayMetrics displayMetrics, int value) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, value, displayMetrics);
    }

    public static float sp2px(DisplayMetrics displayMetrics, int value) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, value, displayMetrics);
    }
}
