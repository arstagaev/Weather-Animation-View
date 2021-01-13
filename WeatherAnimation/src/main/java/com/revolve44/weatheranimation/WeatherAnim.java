package com.revolve44.weatheranimation;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import java.util.Timer;
import java.util.TimerTask;

/**
author @arstagaev, 2020
 */

public class WeatherAnim extends View {

    private final Space space = new Space();
    private final Snow snow = new Snow();
    private final Rain rain = new Rain();

    private Paint paint;

    private final int sizeoval = 4;
    private int typeOfweather = 0; // main
    private int NUMofELEMENTS = 400; // main
    private int SPEED = 4; // main

    private int REFRESHING = 100; // main .border for refresh
    private int halfwidth, halfheight = 0;

    //set sizes
    DisplayMetrics displaymetrics = new DisplayMetrics();
    private int width =  displaymetrics.widthPixels;
    private int height = displaymetrics.heightPixels;




    public WeatherAnim(Context context) {
        super(context);
    }

    public WeatherAnim(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }


    @Override
    protected void onSizeChanged(int xNew, int yNew, int xOld, int yOld){
        super.onSizeChanged(xNew, yNew, xOld, yOld);
        Log.i("sizech","new old"+yNew+" |" + yOld);
        width = xNew;
        height = yNew;
        if (height>0 & width >0){
            switch(typeOfweather) {
                case 0:
                    space.init(NUMofELEMENTS,SPEED,height,width);
                    break;
                case 1:
                    //SPEED = 2;  // recommend
                    snow.init(NUMofELEMENTS,SPEED,height,width);
                    break;
                case 2:
                    //SPEED = 12; // recommend
                    NUMofELEMENTS = 50;
                    rain.init(NUMofELEMENTS,SPEED,height,width);
                    break;

            }
        }

        Log.d("width: "," is "+width+" ][ "+height);
    }


    private void refreshView(){
        /*
        USE this if you need manual real time settings with SeekBars
         */
        //Clear arrays
        snow.onClearArrays();
        space.onClearArrays();

        paint.reset();

    }

    /**
     * Types of animation: 0: space, 1: snow, 2: rain
     * @param TypeofWeather
     */
    public void init(int TypeofWeather) {
        typeOfweather = TypeofWeather;
        onSizeChanged(width,height,width,height);

        paint = new Paint();
        paint.setAntiAlias(true);

    }


    double x = 0;
    double y = 0;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    protected void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);
//        paint.setColor(Color.BLACK);
//        canvas.drawRect(0,0,width,height,paint);
        halfheight = height/2;
        halfwidth = width/2;

        for (int i = 0; i<NUMofELEMENTS; i++){


            //Get X and Y
            switch(typeOfweather) {
                case 0:
                    x = space.xCoord(i);
                    y = space.yCoord(i);
                    break;
                case 1:
                    x = snow.xCoord(i);
                    y = snow.yCoord(i);
                    break;
                case 2:
                    x = rain.xCoord(i);
                    y = rain.yCoord(i);
                    break;
            }

            switch(typeOfweather) {
                case 0:
                    paint.setColor(Color.WHITE);
                    canvas.drawOval((float) (halfwidth+x), (float) (halfheight+y),
                            (float) (halfwidth+sizeoval+x), (float) (halfheight+sizeoval+y),paint);
                    space.JustInTimeUpdate(i,REFRESHING);
                    break;

                case 1:
                    paint.setColor(Color.WHITE);
                    canvas.drawCircle((float) x , (float) y ,2,paint);
                    snow.moveAndCheck(i);
                    break;
                case 2:
                    paint.setColor(Color.BLUE);
                    //canvas.drawCircle((float) x , (float) y ,2,paint);
                    canvas.drawOval((float) (x), (float) (y),
                            (float) (2+x), (float) (20+2+y),paint);
                    rain.moveAndCheck(i);
                    break;
            }
        }

        invalidate();
    }

    /**
     * Types of animation: 0: space, 1: snow, 2: rain
     * @param typeOfweather
     */
    public void setTypeOfweather(int typeOfweather) {
        if (typeOfweather>=0 & typeOfweather<=2){
            this.typeOfweather = typeOfweather;
        }else{ Log.e("ERROR in Library", "WRONG input setTypeOfweather");}
        refreshView();
        invalidate();

    }

    /**
     * Set number of animated elements: from 0 to 10000
     * @param NUMofELEMENTS
     */
    public void setNUMofELEMENTS(int NUMofELEMENTS) {
        if (NUMofELEMENTS>=0 & NUMofELEMENTS<10000){
            this.NUMofELEMENTS = NUMofELEMENTS;
        }else{ Log.e("ERROR in Library", "WRONG input setNUMofELEMENTS");}
        refreshView();
        invalidate();
    }

    /**
     * Speed of animation normal settings: from 4 to 10
     * @param SPEED
     */
    public void setSPEED(int SPEED) {
        if (SPEED>=0){
            this.SPEED = SPEED;
        }else{ Log.e("ERROR in Library", "WRONG input setSPEED");}
        refreshView();
        invalidate();
    }

    public void setREFRESHING(int REFRESHING) {
        if (REFRESHING>=0) {
            this.REFRESHING = REFRESHING;
        }else{ Log.e("ERROR in Library", "WRONG setREFRESHING");}
        refreshView();
        invalidate();
    }
}
