package test.bin.jetpackcompose_template

import android.app.Application
import  test.bin.jetpackcompose_template.di.appModule
import  test.bin.jetpackcompose_template.di.networkModule
import  test.bin.jetpackcompose_template.di.repoModule
import  test.bin.jetpackcompose_template.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.KoinApplication
import org.koin.core.context.startKoin
import org.koin.core.logger.Level



class TemplateAPP : Application() {

    override fun onCreate() {
        super.onCreate()

        // Start Koin
        startKoin {
            androidLogger(Level.DEBUG) // Optional, for logging purposes
            androidContext(this@TemplateAPP) // Application context for Koin
            modules(
                viewModelModule,
                appModule,
                networkModule,
                repoModule
            ) // modules
        }
    }
}