package com.example.weatherapp.repository

import com.example.weatherapp.api.RetrofitInstance
import com.example.weatherapp.model.WeatherCard
import retrofit2.Response
import retrofit2.Retrofit

class Repository {

    suspend fun getCity(cityName: String) : Response<WeatherCard> {
        return RetrofitInstance.api.getCity(cityName)
    }

    suspend fun getAllCities(cities : Array<String>) : List<Response<WeatherCard>> {
        val response : MutableList<Response<WeatherCard>> = mutableListOf();
        for(city in cities) {
            response.add(
                RetrofitInstance.api.getCity(city)
            )
        }
        return response.toList();
    }

}