package com.revolve44.weatheranimation;

import android.util.Log;

import java.util.ArrayList;
import java.util.Random;

public class Snow  implements WeatherPrinciple{

    Random rand = new Random();

    ArrayList<Float> xSnowFlake = new ArrayList<>();
    ArrayList<Float> ySnowFlake = new ArrayList<>();
    ArrayList<Float> speedFlakes = new ArrayList<>();

    int height=0;
    int width=0;

    @Override
    public void init(int NUMofELEMENTS, int SPEED,int heightY, int widthX) {
        height = heightY;
        width = widthX;

        Log.d("snow", "Width = "+ width+ " H= "+height);

        for (int o = 0; o< NUMofELEMENTS; o++){
            //Also init all the drops
            xSnowFlake.add(rand.nextFloat()*widthX);
            ySnowFlake.add(-rand.nextFloat()*heightY);
            speedFlakes.add(rand.nextFloat()*6);

        }
        Log.i("snow array", "xSnow "+xSnowFlake.toString());


    }

    double x = 0;
    double y = 0;

    @Override
    public double xCoord(int i) {
        x = xSnowFlake.get(i);
        return x;
    }

    @Override
    public double yCoord(int i) {
        y = ySnowFlake.get(i);
        return y;
    }

    @Override
    public void onSwitchType(int TypeOfWeather) {

    }


    public void moveAndCheck(int i){
        ySnowFlake.set(i,ySnowFlake.get(i)+speedFlakes.get(i));

        if (ySnowFlake.get(i)>height){
            //ySnowFlake.set(i,rand.nextFloat()*height);
            xSnowFlake.set(i,rand.nextFloat()*width);
            ySnowFlake.set(i,1f);
        }
    }

    @Override
    public void onClearArrays() {
        xSnowFlake.clear();
        ySnowFlake.clear();
        speedFlakes.clear();
    }
}
