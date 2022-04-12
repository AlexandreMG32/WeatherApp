package com.example.weatherapp.api

import com.example.weatherapp.model.WeatherCard
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface WeatherApi {

    @GET("weather?appid=526aaf1f958953cda99fdb0e89e9c26a&units=metric")
    suspend fun getCity(
        @Query("q") cityName: String,
    ) : Response<WeatherCard>;

}