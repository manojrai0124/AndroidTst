package com.manojrai.androidtst.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ForeCastDaily(
    @Expose
    @SerializedName("daily")
    val list: List<Daily>,
) {

    data class Daily(
        @Expose
        @SerializedName("dt")
        val date: Long,

        @Expose
        @SerializedName("pressure")
        val pressure: Double,

        @Expose
        @SerializedName("humidity")
        val humidity: Double,

        @Expose
        @SerializedName("temp")
        val temp: Temp,
    ) {
        data class Temp(
            @Expose
            @SerializedName("day")
            val day: Double,

            @Expose
            @SerializedName("min")
            val min: Double,

            @Expose
            @SerializedName("max")
            val max: Double,

            @Expose
            @SerializedName("night")
            val night: Double,

            @Expose
            @SerializedName("eve")
            val eve: Double,

            @Expose
            @SerializedName("morn")
            val morn: Double,

            )
    }
}