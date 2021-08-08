package com.manojrai.androidtst.di

import com.manojrai.androidtst.data.remote.Networking
import com.manojrai.androidtst.data.repository.DailyForeCastRepository
import com.manojrai.androidtst.data.repository.MainRepository
import com.manojrai.androidtst.ui.forecast.DailyForeCastViewModel
import com.manojrai.androidtst.ui.main.MainViewModel
import com.manojrai.androidtst.utils.rx.RxSchedulerProvider
import com.manojrai.androidtst.utils.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    single {
        Networking.create(
            Networking.BASE_URL,
            androidApplication().cacheDir,
            10 * 1024 * 1024 // 10MB
        )
    }

    factory<SchedulerProvider> { RxSchedulerProvider() }

    factory { CompositeDisposable() }
}


val mainModule = module {

    factory { MainRepository(networkService = get()) }

    viewModel {
        MainViewModel(
            schedulerProvider = get(),
            compositeDisposable = get(),
            mainRepository = get()
        )
    }
}

val foreCast = module {

    factory { DailyForeCastRepository(networkService = get()) }

    viewModel {
        DailyForeCastViewModel(
            schedulerProvider = get(),
            compositeDisposable = get(),
            dailyForeCastRepository = get()
        )
    }
}