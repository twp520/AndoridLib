package com.colin.cusview;

import android.content.Context;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.WindowManager;

import java.util.Objects;

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


    public static float getScreenWidth(Context context) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((WindowManager) Objects.requireNonNull(context.getApplicationContext().getSystemService(Context.WINDOW_SERVICE)))
                .getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.widthPixels;
    }

    public static float getScreenHeight(Context context) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((WindowManager) Objects.requireNonNull(context.getApplicationContext().getSystemService(Context.WINDOW_SERVICE)))
                .getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.heightPixels;
    }
}
