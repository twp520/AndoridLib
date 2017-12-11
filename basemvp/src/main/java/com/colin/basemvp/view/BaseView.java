package com.colin.basemvp.view;

import android.content.Context;

/**
 * Created by tianweiping on 2017/12/11.
 */

public interface BaseView {
    //初始化view
    void initView();

    /**
     * 显示一个loading
     *
     * @param context 上下文
     */
    void showLoading(Context context);

    /**
     * 关闭loading
     */
    void dismissLoading();

    /**
     * 弹一个吐司提示
     *
     * @param code code
     * @param msg  提示信息
     */
    void showTipMessage(int code, String msg);


    /**
     * 获得上下文对象
     *
     * @return 上下文对象
     */
    Context getContext();

    /**
     * 管理 网络请求生命周期的 key
     *
     * @return key
     */
    String getNetKey();

    /**
     * 因为token相关错误需要跳转到登录页面
     */
    void toLoginActBySessionError();
}
