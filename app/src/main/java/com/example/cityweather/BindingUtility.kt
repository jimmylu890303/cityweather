package com.example.cityweather

import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import coil.load

@BindingAdapter("setImage")

fun ImageView.setIconImage(icon: String?) {  //initially, livedata is null
    val accessURL = "${WeatherViewModel.ICON_URL}${icon}@2x.png"   //"https://openweathermap.org/img/wn/"
    val iconUri = accessURL.toUri().buildUpon().scheme("https").build()

    if (icon == null) {
        setImageDrawable(null)  // reset the imageView
    }
    else {
        //use Coil to download the weather icon from the website
        this.load(iconUri)
    }
}
