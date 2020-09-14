package com.ardayuksel.androidroomapp.data.db

import android.content.Context
import android.util.Log
import androidx.datastore.DataStore
import androidx.datastore.preferences.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException

const val PREFERENCE_NAME = "MY_PREFERENCE"

class DataStoreRepository(context: Context) {

    private object PreferenceKeys {
        val username = preferencesKey<String>("db_username")
        val password = preferencesKey<String>("db_password")
    }

    private val dataStore: DataStore<Preferences> = context.createDataStore(
        name = PREFERENCE_NAME
    )

    suspend fun saveToDataStore(username: String, password: String) {
        dataStore.edit { preference ->
            preference[PreferenceKeys.username] = username
            preference[PreferenceKeys.password] = password
        }
    }

    val readUsernameFromDataStore: Flow<String> = dataStore.data.catch { exception ->
        if (exception is IOException) {
            Log.d("DataStore", exception.message.toString())
            emit(emptyPreferences())
        } else {
            throw exception
        }
    }.map { userPreference ->
        val getUserName = userPreference[PreferenceKeys.username] ?: "none"
        getUserName
    }

    val readPasswordFromDataStore: Flow<String> = dataStore.data.catch { exception ->
        if (exception is IOException) {
            Log.d("DataStore", exception.message.toString())
            emit(emptyPreferences())
        } else {
            throw exception
        }
    }.map { passwordPreference ->
        val getPassword = passwordPreference[PreferenceKeys.password] ?: "none"
        getPassword
    }
}