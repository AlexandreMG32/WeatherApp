package com.example.weatherapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.weatherapp.model.WeatherCard

class CityViewModel : ViewModel() {

    private var _city = MutableLiveData<WeatherCard>()
    private val city: LiveData<WeatherCard> = _city;

    fun setCity(weatherCard: WeatherCard) {
        _city.value = weatherCard
    }

    fun getCity() : WeatherCard {
        return city.value!!
    }
}