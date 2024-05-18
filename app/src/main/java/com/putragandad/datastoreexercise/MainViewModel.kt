package com.putragandad.datastoreexercise

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(private val preferences: DataStorePreferences) : ViewModel() {
    fun saveLoginStatus(status : Boolean) = viewModelScope.launch(Dispatchers.IO) {
        preferences.saveLoginStatus(status)
    }

    fun readLoginStatus() = preferences.readLoginStatus.asLiveData()
}

class MainViewModelFactory(
    private val preferences: DataStorePreferences
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MainViewModel(preferences) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}