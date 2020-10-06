package com.example.sampleapplication.database

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.*


@Dao
interface ProfileDao {

    @Query("select * from profiledatabaseentity ")
     fun getAllLikedProfiles(): LiveData<ProfileDatabaseEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
   suspend fun saveProfile(profile: ProfileDatabaseEntity)
}

@Database(entities = [ProfileDatabaseEntity::class], version = 1)
abstract class ProfileDatabase : RoomDatabase() {
    abstract val ProfileDao: ProfileDao


}

private lateinit var INSTANCE: ProfileDatabase

fun getProfileDatabase(context: Context): ProfileDatabase {
    synchronized(ProfileDatabase::class.java) {
        if (!::INSTANCE.isInitialized) {
            INSTANCE = Room.databaseBuilder(
                context.applicationContext,
                ProfileDatabase::class.java,
                "profiles"
            ).build()
        }
    }


    return INSTANCE
}

