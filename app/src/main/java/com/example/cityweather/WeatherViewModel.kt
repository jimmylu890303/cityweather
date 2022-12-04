package com.example.cityweather

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class WeatherViewModel:ViewModel() {

    val cities = listOf<String>("Taipei","New York","London","Tokyo")
    val selectCityWeather = MutableLiveData<CityWeather>();
    fun sendRetrofitRequest(location: String) {
        viewModelScope.launch {
            try {
                val result =
                    GetService.retrofitService.getAppData(location, "metric", "zh_tw", WeatherViewModel.API_KEY)
                val cityWeather = CityWeather(
                    result.name,
                    result.main.temp,
                    result.weather[0].description,
                    result.weather[0].icon
                )
                selectCityWeather.value = cityWeather
                Log.d("Main", cityWeather.toString())
            } catch (e: Exception) {
                Log.d("Main", "Fail to access: ${e.message}")
            }
        }
    }
    companion object{
        const val API_URL="https://api.openweathermap.org/"
        const val API_KEY="f3cf00da52124c991711a1e71fe20974"

    }
}