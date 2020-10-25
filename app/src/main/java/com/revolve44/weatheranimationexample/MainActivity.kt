package com.revolve44.weatheranimationexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.revolve44.weatheranimation.WeatherAnim

class MainActivity : AppCompatActivity() {

    lateinit var wa : WeatherAnim

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        wa = findViewById(R.id.anim)
        wa.init(0)


    }
}