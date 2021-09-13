package com.furkansalkin.weatherapptask.service


import com.furkansalkin.weatherapptask.model.ConsolidatedWeather
import io.reactivex.Single
import retrofit2.http.GET

//https://www.metaweather.com/api/location/44418/
interface WeatherAPI {

    @GET("api/location/44418/")
    fun getData(): Single<ConsolidatedWeather>

}