package com.manojrai.androidtst.ui.main

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.manojrai.androidtst.data.model.Weather
import com.manojrai.androidtst.data.repository.MainRepository
import com.manojrai.androidtst.ui.base.BaseViewModel
import com.manojrai.androidtst.utils.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable

class MainViewModel(
    schedulerProvider: SchedulerProvider,
    compositeDisposable: CompositeDisposable,
    private val mainRepository: MainRepository
) : BaseViewModel(schedulerProvider, compositeDisposable) {

    val weather = MutableLiveData<Weather>()
    val progressbar = MutableLiveData<Boolean>()

    fun getWeatherMain(lat: Double, lon: Double) {
        progressbar.postValue(true)
        compositeDisposable.add(
            mainRepository.getMainWeather(lat.toString(), lon.toString())
                .subscribeOn(schedulerProvider.io())
                .subscribe({
                    progressbar.postValue(false)
                    weather.postValue(it)
                }, {
                    progressbar.postValue(false)
                    messageString.postValue(it.message)
                })
        )
    }
}