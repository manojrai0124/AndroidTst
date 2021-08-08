package com.manojrai.androidtst.data.remote

import com.manojrai.androidtst.data.model.ForeCastDaily
import com.manojrai.androidtst.data.model.Weather
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface NetworkService {

    @GET(EndPoints.WEATHER)
    fun getMainWeather(
        @Query("lat") lat: String,
        @Query("lon") lon: String,
        @Query("appid") appid: String = Networking.API_KEY,
    ): Single<Weather>


    @GET(EndPoints.FORECAST_DAILY)
    fun getForeCastDaily(
        @Query("lat") lat: String,
        @Query("lon") lon: String,
        @Query("exclude") exclude: String = "hourly,minutely",
        @Query("appid") appid: String = Networking.API_KEY,
    ): Single<ForeCastDaily>
}