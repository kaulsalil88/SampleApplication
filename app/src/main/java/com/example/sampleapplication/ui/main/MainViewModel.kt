package com.example.sampleapplication.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sampleapplication.network.Profile
import com.example.sampleapplication.network.ProfileNetwork
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    private var _profile = MutableLiveData<Profile>()

    val profile: LiveData<Profile> get() = _profile


    private var _eventNetworkError = MutableLiveData<Boolean>(false)


    val eventNetworkError: LiveData<Boolean>
        get() = _eventNetworkError


    private var _isNetworkErrorShown = MutableLiveData<Boolean>(false)


    val isNetworkErrorShown: LiveData<Boolean>
        get() = _isNetworkErrorShown


    fun getProfile() {
        viewModelScope.launch {
            val profileContainer = ProfileNetwork.profiles.getProfile()
            _profile.value = profileContainer.results.get(0)

        }
    }

}