package com.terabyte.passworder.util

import android.content.Context
import com.terabyte.passworder.application.MyApplication
import com.terabyte.passworder.di.AppComponent

@Suppress("RecursivePropertyAccessor")
val Context.appComponent: AppComponent
    get() = when(this) {
        is MyApplication -> appComponent
        else -> this.applicationContext.appComponent
    }