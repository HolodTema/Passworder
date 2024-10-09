package com.terabyte.passworder.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.terabyte.passworder.ROOM_DATABASE_VERSION


@Database(entities = [Password::class], version = ROOM_DATABASE_VERSION)
abstract class MyRoomDatabase: RoomDatabase() {
    abstract fun passwordDao(): PasswordDao
}