package com.colin.andoridlib;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.colin.cusview.YbbView;

public class TestActivity extends AppCompatActivity {

    private YbbView ybbView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
//        ybbView = (YbbView) findViewById(R.id.test_ybb);
    }

    public void startAnim(View view) {
        ybbView.stopAnim();
        ybbView.startAnim();
    }
}
