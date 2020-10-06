package com.example.sampleapplication.database

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "profiledatabaseentity")
data class ProfileDatabaseEntity(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val title: String,
    val first: String,
    val last: String
)