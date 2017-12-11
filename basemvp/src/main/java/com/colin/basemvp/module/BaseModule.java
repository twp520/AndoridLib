package com.colin.basemvp.module;

/**
 * Created by tianweiping on 2017/12/11.
 */

public interface BaseModule {

    /**
     * 获取当前时间
     *
     * @return 当前时间
     */
    String getCurrentTime();

    /**
     * 获取token
     *
     * @return token
     */
    String getToken();
}
