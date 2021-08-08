package com.manojrai.androidtst.data.repository

import com.manojrai.androidtst.data.remote.NetworkService

class DailyForeCastRepository(
    private val networkService: NetworkService
) {
    fun getForeCast(lat: String, lon: String) =
        networkService.getForeCastDaily(lat, lon)

}