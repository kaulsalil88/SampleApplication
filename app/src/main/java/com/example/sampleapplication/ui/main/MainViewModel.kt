package com.example.sampleapplication.ui.main

import android.app.Application
import android.content.Context
import androidx.lifecycle.*
import com.example.sampleapplication.database.getProfileDatabase
import com.example.sampleapplication.network.Profile
import com.example.sampleapplication.network.ProfileNetwork
import com.example.sampleapplication.network.convertToDbEntity
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private var _profiles = MutableLiveData<List<Profile>>()

    val profiles: LiveData<List<Profile>> get() = _profiles

    private val listOfProfiles = mutableListOf<Profile>()

    private var _eventNetworkError = MutableLiveData<Boolean>(false)


    val eventNetworkError: LiveData<Boolean>
        get() = _eventNetworkError


    private var _isNetworkErrorShown = MutableLiveData<Boolean>(false)


    val isNetworkErrorShown: LiveData<Boolean>
        get() = _isNetworkErrorShown


    //Fetching 15 items at a time
    fun getProfile() {
        viewModelScope.launch {
            for (i in 0..5) {
                val profileContainer = ProfileNetwork.profiles.getProfile()
                listOfProfiles.add(profileContainer.results[0])
            }
            _profiles.value = listOfProfiles

        }
    }

    //Function to save the profile in the database
    fun saveFavouriteProfile(profile: Profile) {
        viewModelScope.launch {
            getProfileDatabase(getApplication()).ProfileDao.saveProfile(profile.convertToDbEntity())
        }


    }


}