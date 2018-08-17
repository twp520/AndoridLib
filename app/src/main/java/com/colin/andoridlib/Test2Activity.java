package com.colin.andoridlib;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.colin.cusview.planet.GalaxyView;

import java.util.ArrayList;

public class Test2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test2);

        GalaxyView galaxyView = (GalaxyView) findViewById(R.id.test2_galaxy);
        ArrayList<String> data = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            data.add("星球" + i);
        }
        TextGalaxyAdapter adapter = new TextGalaxyAdapter(data);
        galaxyView.setAdapter(adapter);
    }

    public void startTurn(View view) {

    }
}
