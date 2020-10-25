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

/*
author @arstagaev, 2020
 */

public class WeatherAnim extends View {

    Space space = new Space();
    Snow snow = new Snow();
    Rain rain = new Rain();

    private Paint paint;

    private int sizeoval = 4;



    public int typeOfweather = 0; // main
    public int NUMofELEMENTS = 400; // main
    public int DELAY_FPS = 2; //main
    public int SPEED = 4; // main

    private int REFRESHING = 100; // main .border for refresh

    private int halfwidth, halfheight = 0;



    //set sizes
    DisplayMetrics displaymetrics = new DisplayMetrics();
    public int width =  displaymetrics.widthPixels;
    public int height = displaymetrics.heightPixels;




    public WeatherAnim(Context context) {
        super(context);
    }

    public WeatherAnim(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        //init(context, attrs);
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
                    //SPEED = 2;  // recomend
                    snow.init(NUMofELEMENTS,SPEED,height,width);
                    break;
                case 2:
                    //SPEED = 12; // recomend
                    NUMofELEMENTS = 50;
                    rain.init(NUMofELEMENTS,SPEED,height,width);
                    break;

            }
        }

        Log.d("width: "," is "+width+" ][ "+height);
    }


    public void refreshView(){
        /*
        USE this if you need manual real time settings with SeekBars
         */
        //Clear arrays
        snow.onClearArrays();
        space.onClearArrays();

        paint.reset();

    }

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

        try { Thread.sleep(DELAY_FPS); }
        catch (Exception e) { e.printStackTrace(); }
        invalidate();
    }

    public void setTypeOfweather(int typeOfweather) {
        if (typeOfweather>=0 & typeOfweather<=2){
            this.typeOfweather = typeOfweather;
        }else{ Log.e("ERROR in Library", "WRONG input NUM");}
        refreshView();
        invalidate();

    }

    public void setNUMofELEMENTS(int NUMofELEMENTS) {
        if (NUMofELEMENTS>=0 & NUMofELEMENTS<10000){
            this.NUMofELEMENTS = NUMofELEMENTS;
        }else{ Log.e("ERROR in Library", "WRONG input NUM");}
        refreshView();
        invalidate();
    }

    public void setDELAY_FPS(int DELAY_FPS) {
        if (DELAY_FPS>=0){
            this.DELAY_FPS = DELAY_FPS;
        }else{ Log.e("ERROR in Library", "WRONG input NUM");}
        refreshView();
        invalidate();
    }

    public void setSPEED(int SPEED) {
        if (SPEED>=0){
            this.SPEED = SPEED;
        }else{ Log.e("ERROR in Library", "WRONG input NUM");}
        refreshView();
        invalidate();
    }

    public void setREFRESHING(int REFRESHING) {
        if (REFRESHING>=0) {
            this.REFRESHING = REFRESHING;
        }else{ Log.e("ERROR in Library", "WRONG NUM");}
        refreshView();
        invalidate();
    }
}
