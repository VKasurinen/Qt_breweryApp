package com.vkasurinen.qt_project

import android.app.Application
import com.vkasurinen.qt_project.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class BreweryApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@BreweryApplication)
            modules(appModule)
        }
    }
}