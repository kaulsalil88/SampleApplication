package com.example.sampleapplication.ui.main

import android.app.Application
import android.provider.ContactsContract
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.sampleapplication.database.ProfileDatabaseEntity
import com.example.sampleapplication.database.getProfileDatabase
import com.example.sampleapplication.network.Profile
import kotlinx.coroutines.async

class FavoritesViewModel(application: Application) : AndroidViewModel(application) {

    private var _profiles = MutableLiveData<List<ProfileDatabaseEntity>>()

    val profiles: LiveData<List<ProfileDatabaseEntity>> get() = _profiles

    fun getFavoritesFromDb(){
        Log.d("FavViewModel","getFavoritesFromDb")
        viewModelScope.async {
           _profiles.value = getProfileDatabase(getApplication()).ProfileDao.getAllLikedProfiles()
        }

    }
}