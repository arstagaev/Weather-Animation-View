package com.revolve44.weatheranimationexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.revolve44.weatheranimation.WeatherAnim

class MainActivity : AppCompatActivity() {

    lateinit var weatherAnim : WeatherAnim

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        weatherAnim = findViewById(R.id.anim)
        weatherAnim.init(2)

        weatherAnim.setSPEED(4)
        weatherAnim.setNUMofELEMENTS(10000)


    }
}