package com.colin.andoridlib;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.colin.cusview.DTSView;
import com.colin.cusview.HeartFunctionView;
import com.colin.cusview.MovePathView;
import com.colin.cusview.QuadWavePathView;
import com.colin.cusview.WavePathView;
import com.colin.cusview.YbbView;

public class TestActivity extends AppCompatActivity {

    private YbbView ybbView;

    private WavePathView wavePathView;

    private QuadWavePathView qwpView;

    private MovePathView mpv;

    private HeartFunctionView hfv;

    private Button testTarget;
    private DTSView dtsView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
//        ybbView = (YbbView) findViewById(R.id.test_ybb);
//        wavePathView = (WavePathView) findViewById(R.id.test_wave);
//        qwpView = (QuadWavePathView) findViewById(R.id.test_qwpv);
//        mpv = (MovePathView) findViewById(R.id.test_mpv);
//        hfv = (HeartFunctionView) findViewById(R.id.test_hfv);
        testTarget = (Button) findViewById(R.id.test_target);
        dtsView = (DTSView) findViewById(R.id.test_dtsv);


    }

    /*@Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus){
            new GuideView.Builder()
                    .arrowBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.guide_arrow))
                    .guideText("我是引导文字~~~~你好吗？、、\n哈哈哈")
                    .lightShapeType(GuideView.RECT)
                    .targetView(testTarget)
                    .build(this)
                    .addToActivity(this);
        }
    }*/

    public void startWave(View view) {
//        wavePathView.reset();
//        wavePathView.startAnim();
//        Log.e("colin", "startWave: sin30 = " + Math.sin(30));
//        qwpView.startWaveAnim();
//        mpv.startMove();
//        hfv.startFunction();
       /* new GuideView.Builder()
                .arrowBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.guide_arrow))
                .guideText("我是引导文字~~~~你好吗？、、\n哈哈哈")
                .lightShapeType(GuideView.RECT)
                .targetView(testTarget)
                .build(this)
                .addToActivity(this);*/
        dtsView.startAnim();

    }

    public void startAnim(View view) {
        ybbView.stopAnim();
        ybbView.startAnim();
    }
}
