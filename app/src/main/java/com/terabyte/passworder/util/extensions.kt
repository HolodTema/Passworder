package com.terabyte.passworder.util

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.terabyte.passworder.application.MyApplication
import com.terabyte.passworder.data.DataStoreManager
import com.terabyte.passworder.di.AppComponent

@Suppress("RecursivePropertyAccessor")
val Context.appComponent: AppComponent
    get() = when(this) {
        is MyApplication -> appComponent
        else -> this.applicationContext.appComponent
    }

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = DataStoreManager.DATASTORE_PREFERENCES_NAME)