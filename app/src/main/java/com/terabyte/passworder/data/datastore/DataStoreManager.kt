package com.terabyte.passworder.data.datastore

import android.content.Context
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.terabyte.passworder.util.dataStore
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Singleton

@Singleton
class DataStoreManager(private val context: Context) {

    fun <T> readFromDataStore(key: Preferences.Key<T>, listener: (T?) -> Unit) {
        CoroutineScope(Dispatchers.Main).launch {
            val deferred = async(Dispatchers.IO) {
                context.dataStore.data.first()[key]
            }
            listener(deferred.await())
        }
    }

    fun <T> writeToDataStore(key: Preferences.Key<T>, value: T, listener: () -> Unit) {
        CoroutineScope(Dispatchers.Main).launch {
            val deferred = async(Dispatchers.IO) {
                context.dataStore.edit { preferences ->
                    preferences[key] = value
                }
                Unit
            }
            deferred.await()
            listener()
        }

    }
    companion object {
        const val DATASTORE_PREFERENCES_NAME = "prefs"
        val KEY_LOGIN_PASSWORD = stringPreferencesKey("loginPassword")
    }
}