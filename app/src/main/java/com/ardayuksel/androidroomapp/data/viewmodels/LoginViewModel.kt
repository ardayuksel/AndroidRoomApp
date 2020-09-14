package com.ardayuksel.androidroomapp.data.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.ardayuksel.androidroomapp.data.db.DataStoreRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LoginViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = DataStoreRepository(application)

    val readUsernameFromDataStore = repository.readUsernameFromDataStore.asLiveData()
    val readPasswordFromDataStore = repository.readPasswordFromDataStore.asLiveData()

}