package com.terabyte.passworder.data.room

import android.content.Context
import androidx.room.Room
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Singleton

@Singleton
class RoomManager(context: Context) {
    private val db = Room.databaseBuilder(
        context,
        MyRoomDatabase::class.java,
        DB_NAME
    ).build()

    fun getAllPasswords(listener: (List<Password>) -> Unit) {
        CoroutineScope(Dispatchers.Main).launch {
            val deferred = async(Dispatchers.IO) {
                db.passwordDao().getAll()
            }
            listener(deferred.await())
        }
    }

    fun insertPassword(password: Password) {
        CoroutineScope(Dispatchers.IO).launch {
            db.passwordDao().insert(password)
        }
    }

    fun updatePassword(password: Password) {
        CoroutineScope(Dispatchers.IO).launch {
            db.passwordDao().update(password)
        }
    }

    fun deletePassword(password: Password) {
        CoroutineScope(Dispatchers.IO).launch {
            db.passwordDao().delete(password)
        }
    }

    companion object {
        private const val DB_NAME = "PasswordDatabase"
    }
}