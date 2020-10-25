package com.revolve44.weatheranimation;

import android.util.Log;

import java.util.ArrayList;
import java.util.Random;

public class Rain implements WeatherPrinciple {

    Random rand = new Random();

    ArrayList<Float> xDrop = new ArrayList<>();
    ArrayList<Float> yDrop = new ArrayList<>();
    ArrayList<Float> speedDrops = new ArrayList<>();

    int height=0;
    int width=0;

    @Override
    public void init(int NUMofELEMENTS, int SPEED, int heightY, int widthX) {
        height = heightY;
        width = widthX;

        Log.d("snow", "Width = "+ width+ " H= "+height);

        for (int o = 0; o< NUMofELEMENTS; o++){
            //Also init all the drops
            xDrop.add(rand.nextFloat()*widthX);
            yDrop.add(-rand.nextFloat()*heightY);
            speedDrops.add(1f*SPEED);

        }
        Log.i("snow array", "xSnow "+ xDrop.toString());

    }

    double x = 0;
    double y = 0;

    @Override
    public double xCoord(int i) {
        x = xDrop.get(i);
        return x;
    }

    @Override
    public double yCoord(int i) {
        y = yDrop.get(i);
        return y;
    }

    @Override
    public void onSwitchType(int TypeOfWeather) {

    }


    public void moveAndCheck(int i){
        yDrop.set(i, yDrop.get(i)+ speedDrops.get(i));

        if (yDrop.get(i)>height){
            //ySnowFlake.set(i,rand.nextFloat()*height);
            xDrop.set(i,rand.nextFloat()*width);
            yDrop.set(i,1f);
        }
    }

    @Override
    public void onClearArrays() {
        xDrop.clear();
        yDrop.clear();
        speedDrops.clear();
    }
}
