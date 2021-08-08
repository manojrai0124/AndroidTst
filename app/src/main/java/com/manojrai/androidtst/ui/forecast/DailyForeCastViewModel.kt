package com.manojrai.androidtst.ui.forecast

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.manojrai.androidtst.data.model.ForeCastDaily
import com.manojrai.androidtst.data.repository.DailyForeCastRepository
import com.manojrai.androidtst.ui.base.BaseViewModel
import com.manojrai.androidtst.utils.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable

class DailyForeCastViewModel(
    schedulerProvider: SchedulerProvider,
    compositeDisposable: CompositeDisposable,
    private val dailyForeCastRepository: DailyForeCastRepository
) : BaseViewModel(schedulerProvider, compositeDisposable) {

    val data = MutableLiveData<List<ForeCastDaily.Daily>>()
    val progress = MutableLiveData<Boolean>()

    fun getDailyForeCast(lat: String, lon: String) {
        progress.postValue(true)
        compositeDisposable.add(
            dailyForeCastRepository.getForeCast(lat, lon)
                .subscribeOn(schedulerProvider.io())
                .subscribe({
                    progress.postValue(false)
                    data.postValue(it.list)

                }, {
                    messageString.postValue(it.message)
                    progress.postValue(false)
                })
        )
    }
}