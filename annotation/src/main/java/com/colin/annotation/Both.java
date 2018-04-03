package com.colin.annotation;

/**
 * Created by tianweiping on 2018/3/27.
 */

public class Both extends Father {

    public static void loge() {
        System.out.println("both's static loge method");
    }

    @Override
    public void logi() {
        System.out.println("both's  logi method");
    }
}
