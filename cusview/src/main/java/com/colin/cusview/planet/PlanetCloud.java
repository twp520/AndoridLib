package com.colin.cusview.planet;

import java.util.ArrayList;

/**
 * create by colin
 * <p>
 * 星球的计算类
 */
public class PlanetCloud {

    private ArrayList<Planet> mPlanets;
    private float centerX, centerY, radius;

    PlanetCloud() {
        mPlanets = new ArrayList<>();
    }

    //计算每个星球的初始坐标
    public void create() {
        for (int i = 0; i < mPlanets.size(); i++) {
            Planet planet = mPlanets.get(i);
            planet.loc2DY = i * 80;
            planet.loc2DX = i * 50;
            planet.scale = 1f;
        }
    }


    public void setCenterAndRadius(float centerX, float centerY, float radius) {
        this.centerX = centerX;
        this.centerY = centerY;
        this.radius = radius;
    }

    public void add(Planet planet) {
        mPlanets.add(planet);
    }

    public ArrayList<Planet> getPlanets() {
        return mPlanets;
    }

    public Planet get(int position) {
        return mPlanets.get(position);
    }

    public void clear() {
        mPlanets.clear();
    }
}
