package com.colin.basemvp.view;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.colin.basemvp.net.RxNetLife;
import com.colin.basemvp.presenter.BasePresenter;

/**
 *
 *  Created by tianweiping on 2017/12/11.
 */

public abstract class BaseActivity<P extends BasePresenter> extends AppCompatActivity implements BaseView {
    private ProgressDialog loadDialog;
    private Toolbar toolbar;
    private TextView mTextTitle;
    public P mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        beforeSetContent();
        setContentView(getContentViewRes());
//        ButterKnife.bind(this);
        initView();
        onCreateInit(savedInstanceState);
        init();
    }

    public abstract int getContentViewRes();

    public abstract P createPresenter();

    @Override
    public void initView() {
//        toolbar = (Toolbar) findViewById(R.id.base_toolbar);
        if (toolbar != null) {
            setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setDisplayShowTitleEnabled(false);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//            toolbar.setNavigationOnClickListener(v -> finish());
        }
        mPresenter = createPresenter();
        if (mPresenter != null)
            mPresenter.onAttach(getContext());
    }

    public void setTitleBackgroundDrawable(@DrawableRes int drawable) {
        toolbar.setBackgroundResource(drawable);
    }

    public void setTitleText(String title) {
        toolbar.setTitle(title);
    }

    public void showTitleCenter(String title, int color) {
        getSupportActionBar().setDisplayShowHomeEnabled(false);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
//        mTextTitle = (TextView) findViewById(R.id.base_toolbar_text);
        if (mTextTitle != null) {
            mTextTitle.setText(title);
            mTextTitle.setVisibility(View.VISIBLE);
        }
        toolbar.setBackgroundColor(color);
    }

    public void beforeSetContent() {
    }

    public void onCreateInit(@Nullable Bundle savedInstanceState) {
    }

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
//        ToastUtils.showShort(msg);
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
    public void toLoginActBySessionError() {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        RxNetLife.getNetLife().clear(getNetKey());
        if (mPresenter != null)
            mPresenter.onDestroy();
        mPresenter = null;
    }
}
