package com.bin.emotion_detector

import android.app.Application
import  com.bin.emotion_detector.di.appModule

import  com.bin.emotion_detector.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level


class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        // Start Koin
        startKoin {
            androidLogger(Level.DEBUG) // Optional, for logging purposes
            androidContext(this@MyApplication) // Application context for Koin
            modules(
                viewModelModule,
                appModule,

                ) // modules
        }
    }
}