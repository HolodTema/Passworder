package com.terabyte.passworder.data.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Password(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val name: String,
    val password: String,
    val description: String
)
