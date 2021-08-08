package com.manojrai.androidtst

import android.app.Application
import android.content.Context
import com.manojrai.androidtst.di.appModule
import com.manojrai.androidtst.di.foreCast
import com.manojrai.androidtst.di.mainModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class App : Application() {

    companion object {
        private lateinit var app: App

        fun getInstance(): App {
            return app
        }
    }

    override fun onCreate() {
        super.onCreate()
        app = this
        startKoin {
            androidLogger()
            androidContext(this@App)
            koin.loadModules(
                listOf(
                    appModule,
                    mainModule,
                    foreCast
                )
            )
        }
    }

    fun getAppContext(): Context {
        return applicationContext
    }
}