package com.terabyte.passworder.application

import android.app.Application
import com.terabyte.passworder.di.AppComponent
import com.terabyte.passworder.di.AppModule
import com.terabyte.passworder.di.DaggerAppComponent

class MyApplication: Application() {
    lateinit var appComponent: AppComponent

    override fun onCreate() {
        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this.applicationContext))
            .build()
        super.onCreate()
    }
}

