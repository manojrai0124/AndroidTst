package com.manojrai.androidtst.data.repository

import com.manojrai.androidtst.data.remote.NetworkService

class MainRepository(
    private val networkService: NetworkService
) {

    fun getMainWeather(lat: String, lon: String) =
        networkService.getMainWeather(lat, lon)
}