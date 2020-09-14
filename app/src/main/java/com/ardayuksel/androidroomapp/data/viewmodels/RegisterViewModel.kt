package com.ardayuksel.androidroomapp.data.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.ardayuksel.androidroomapp.data.db.DataStoreRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RegisterViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = DataStoreRepository(application)

    val readUsernameFromDataStore = repository.readUsernameFromDataStore.asLiveData()
    val readPasswordFromDataStore = repository.readPasswordFromDataStore.asLiveData()

    fun saveToDataStore(username: String,password: String) =
        viewModelScope.launch(Dispatchers.IO) {
            repository.saveToDataStore(username,password)
        }
}