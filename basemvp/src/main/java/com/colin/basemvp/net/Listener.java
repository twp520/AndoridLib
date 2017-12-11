package com.colin.basemvp.net;


import com.colin.basemvp.bean.BaseResultBean;
import com.colin.basemvp.view.BaseView;
import com.google.gson.JsonSyntaxException;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by tianweiping on 2017/12/11.
 */

public abstract class Listener<T> implements Observer<BaseResultBean<T>> {

    private BaseView view;

    public Listener(BaseView view) {
        this.view = view;
    }

    @Override
    public void onSubscribe(Disposable d) {
        if (view != null)
            RxNetLife.getNetLife().add(view.getNetKey(), d);
    }

    @Override
    public void onNext(BaseResultBean<T> t) {
        try {
            if (view != null)
                view.dismissLoading();
            if (!NetConstant.RESULT_CODE_SUCCESS.equals(t.getRETCODE())) {
                if (NetConstant.RESULT_CODE_TOKEN_ERROR.equals(t.getRETCODE())) {
                    if (view != null)
                        view.toLoginActBySessionError();
                } else {
                    if (view != null)
                        view.showTipMessage(0, t.getRETMSG());
                    onFail(0, t.getRETMSG());
                    onFail(t.getRETCODE(), t.getRETMSG());
                }
            } else {
                onSuccess(t.getARRAYDATA());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public abstract void onSuccess(T data);

    public void onFail(int code, String msg) {

    }

    public void onFail(String code, String msg) {

    }

    @Override
    public void onError(Throwable e) {
        e.printStackTrace();
        if (view != null)
            view.dismissLoading();
        if (view == null)
            return;
        if (e instanceof JsonSyntaxException)
            view.showTipMessage(NetConstant.HTTP_CODE_ERROR_JSON, NetConstant.HTTP_STRING_ERROR);
        else view.showTipMessage(NetConstant.HTTP_CODE_ERROR_NET, NetConstant.HTTP_STRING_ERROR);
        view = null;
    }

    @Override
    public void onComplete() {
        if (view != null)
            view.dismissLoading();
        view = null;
    }
}
