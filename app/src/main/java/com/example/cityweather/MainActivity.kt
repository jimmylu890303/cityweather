package com.example.cityweather

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        sendRetrofitRequest("Taipei")

    }
    private fun sendRetrofitRequest(location: String) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val result =
                    GetService.retrofitService.getAppData(location, "metric", "zh_tw", "f3cf00da52124c991711a1e71fe20974")
                val cityWeather = CityWeather(
                    result.name,
                    result.main.temp,
                    result.weather[0].description,
                    result.weather[0].icon
                )
                Log.d("Main", cityWeather.toString())
            } catch (e: Exception) {
                Log.d("Main", "Fail to access: ${e.message}")
            }
        }
    }

}