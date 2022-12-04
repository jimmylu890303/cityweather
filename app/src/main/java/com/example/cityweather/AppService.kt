package com.example.cityweather

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface AppService {
    @GET("data/2.5/weather?")
    suspend fun getAppData(@Query("q") location:String, @Query("units") unit: String, @Query("lang") lang: String,
                           @Query("appid") api_key: String): WeatherData}

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .baseUrl("https://api.openweathermap.org/")    //"https://api.openweathermap.org/"
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .build()

object GetService {
    val retrofitService : AppService by lazy {
        retrofit.create(AppService::class.java) }
}
