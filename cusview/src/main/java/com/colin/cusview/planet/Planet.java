package com.colin.cusview.planet;

import android.view.View;

/**
 * create by colin
 * <p>
 * 星球的包装类
 */
class Planet {

    public View mView;  //展示的view
    public float loc2DX; //在星系里的2d x坐标
    public float loc2DY; //在星系里的2d y坐标
    public float scale = 1f; //缩放倍数
    public float popularity = 1f; //表示当前是否再 ‘前面’
}
