package com.terabyte.passworder.data.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface PasswordDao {

    @Insert
    fun insert(password: Password)

    @Update
    fun update(password: Password)

    @Delete
    fun delete(password: Password)

    @Query("SELECT * FROM password")
    fun getAll(): List<Password>
}