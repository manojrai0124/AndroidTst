package com.manojrai.androidtst.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Weather(
    @Expose
    @SerializedName("main")
    val main: Main,

    @Expose
    @SerializedName("name")
    val cityName: String,
) {
    data class Main(
        @Expose
        @SerializedName("temp")
        val temp: Double,

        @Expose
        @SerializedName("feels_like")
        val feelsLike: Double,

        @Expose
        @SerializedName("temp_min")
        val tempMin: Double,

        @Expose
        @SerializedName("temp_max")
        val tempMax: Double,

        @Expose
        @SerializedName("pressure")
        val pressure: Double,

        @Expose
        @SerializedName("humidity")
        val humidity: Double,
    )
}