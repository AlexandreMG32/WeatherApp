package com.example.weatherapp

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.model.WeatherCard
import com.example.weatherapp.repository.Repository
import kotlinx.coroutines.launch
import retrofit2.Response

class MainViewModel(private val repository: Repository) : ViewModel() {

    val myResponse: MutableLiveData<List<Response<WeatherCard>>> = MutableLiveData()

    fun getAllCities(citiesArray: Array<String>) {
        viewModelScope.launch {
            myResponse.value = repository.getAllCities(citiesArray);
        }
    }
}