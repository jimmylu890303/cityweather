package com.example.cityweather

import android.os.Binder
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.cityweather.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: WeatherViewModel
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(WeatherViewModel::class.java)
        viewModel.sendRetrofitRequest("Taipei")


        binding.viewModel = viewModel
        binding.lifecycleOwner= this

        val adapter = ArrayAdapter(this,android.R.layout.simple_spinner_item,viewModel.cities )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item)
        binding.spinner.adapter = adapter
        binding.spinner.onItemSelectedListener = object:AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                val selectedPosition = p0?.selectedItemPosition
                selectedPosition?.let{
                    viewModel.sendRetrofitRequest(viewModel.cities[it])
                }
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

        }
    }


}