package com.terabyte.passworder.data.room

import android.content.Context
import androidx.room.Room
import javax.inject.Singleton

@Singleton
class RoomManager(context: Context) {
    private val db = Room.databaseBuilder(
        context,
        MyRoomDatabase::class.java,
        DB_NAME
    )

    companion object {
        private const val DB_NAME = "PasswordDatabase"
    }
}