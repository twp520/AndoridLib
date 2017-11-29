package com.colin.andoridlib;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

import com.colin.annotation.WatchEdit;
import com.colin.annotation_api.ViewAnnoUtil;

public class MainActivity extends AppCompatActivity {

    @WatchEdit(editIds = {R.id.ed_1, R.id.ed_2, R.id.ed_3})
    Button button1;

    @WatchEdit(editIds = {R.id.ed_4, R.id.ed_5})
    Button button2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button1 = (Button) findViewById(R.id.bbb11);
        button2 = (Button) findViewById(R.id.bbb22);
        ViewAnnoUtil.watch(this);
        button1.setEnabled(false);
        button2.setEnabled(false);
    }
}
