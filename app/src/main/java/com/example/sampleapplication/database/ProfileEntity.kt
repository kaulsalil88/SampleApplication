package com.example.sampleapplication.database

import androidx.room.Entity


@Entity
data class ProfileDatabaseEntity(val title: String, val first: String, val last: String)