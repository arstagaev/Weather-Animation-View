package com.revolve44.weatheranimation;

public interface WeatherPrinciple {

     void init(int NUMofELEMENTS, int SPEED, int heightY, int widthX);

     double xCoord(int i);

     double yCoord(int i);

     void onSwitchType(int TypeOfWeather);

     void onClearArrays();
}
