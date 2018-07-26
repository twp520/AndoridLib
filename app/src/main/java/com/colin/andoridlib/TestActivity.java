package com.colin.andoridlib;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.colin.cusview.QuadWavePathView;
import com.colin.cusview.WavePathView;
import com.colin.cusview.YbbView;

public class TestActivity extends AppCompatActivity {

    private YbbView ybbView;

    private WavePathView wavePathView;

    private QuadWavePathView qwpView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
//        ybbView = (YbbView) findViewById(R.id.test_ybb);
//        wavePathView = (WavePathView) findViewById(R.id.test_wave);
        qwpView = (QuadWavePathView) findViewById(R.id.test_qwpv);
    }

    public void startWave(View view) {
//        wavePathView.reset();
//        wavePathView.startAnim();
//        Log.e("colin", "startWave: sin30 = " + Math.sin(30));
        qwpView.startWaveAnim();
    }

    public void startAnim(View view) {
        ybbView.stopAnim();
        ybbView.startAnim();
    }
}
