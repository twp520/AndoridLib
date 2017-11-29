package com.colin.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by tianweiping on 2017/11/28.
 * <p>
 * 观察输入框的注解
 */

@Retention(RetentionPolicy.CLASS)
@Target(ElementType.FIELD)
public @interface WatchEdit {

    /**
     * 被观察的输入框的id
     *
     * @return
     */
    int[] editIds();
}
