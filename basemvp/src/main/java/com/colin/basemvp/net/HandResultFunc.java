package com.colin.basemvp.net;

import com.colin.basemvp.bean.BaseResultBean;

import io.reactivex.functions.Function;

/**
 * Created by tianweiping on 2018/1/30.
 */

public class HandResultFunc<T> implements Function<BaseResultBean<T>, T> {
    @Override
    public T apply(BaseResultBean<T> t) throws Exception {
        if (t.getRETCODE() != NetConstant.RESULT_CODE_SUCCESS) {
            throw new ApiException(t.getRETCODE(), t.getRETMSG());
        }
        return t.getARRAYDATA();
    }
}
