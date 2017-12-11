package com.colin.basemvp.view;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.colin.basemvp.net.RxNetLife;

/**
 * Created by tianweiping on 2017/12/11.
 */

public abstract class BaseActivity extends AppCompatActivity implements BaseView {
    private ProgressDialog loadDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentViewRes());
        initView();
        init();
    }

    public abstract int getContentViewRes();

    /**
     * 初始化一些事
     */
    public void init() {
    }

    @Override
    public void showLoading(Context context) {
        if (loadDialog == null) {
            loadDialog = new ProgressDialog(this);
            loadDialog.setMessage("请稍后...");
            loadDialog.setCancelable(false);
        }
        loadDialog.show();
    }

    @Override
    public void dismissLoading() {
        loadDialog.dismiss();
    }

    @Override
    public void showTipMessage(int code, String msg) {
        //TODO 显示一个提示信息
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public String getNetKey() {
        return getClass().getSimpleName();
    }

    protected void toActivity(Context context, Class activityClass,
                              Bundle bundle, boolean finish) {
        Intent intent = new Intent(context, activityClass);
        if (null != bundle) {
            intent.putExtras(bundle);
        }
        context.startActivity(intent);
        if (finish)
            finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        RxNetLife.getNetLife().clear(getNetKey());
    }
}
