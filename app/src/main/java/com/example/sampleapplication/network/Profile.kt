package com.example.sampleapplication.network

import com.example.sampleapplication.database.ProfileDatabaseEntity
import com.squareup.moshi.JsonClass

//https://randomuser.me/api/0.4/?randomapi

@JsonClass(generateAdapter = true)
data class ProfileContainer(val results: List<Profile>)

@JsonClass(generateAdapter = true)
data class Profile(val user: User)

@JsonClass(generateAdapter = true)
data class User(val gender: String, val name: Name, val picture: String)


//"title":"miss","first":"arlene","last":"bishop"
@JsonClass(generateAdapter = true)
data class Name(val title: String, val first: String, val last: String)

fun Profile.convertToDbEntity(): ProfileDatabaseEntity {
    val profileDatabaseEntity =
        ProfileDatabaseEntity(0, this.user.name.title, this.user.name.first, this.user.name.last)
    return profileDatabaseEntity
}