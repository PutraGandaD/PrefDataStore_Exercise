package com.putragandad.datastoreexercise

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException

class DataStorePreferences(private val context: Context) {
    // initialize DataStore
    val Context.dataStore : DataStore<Preferences> by preferencesDataStore(PrefDataStoreConstant.PREFDATASTORE)

    // run in coroutines for saving to the datastore
    suspend fun saveLoginStatus(status: Boolean) {
        context.dataStore.edit { preferences ->
            preferences[PrefDataStoreConstant.ISLOGIN] = status
        }
    }

    // run in coroutines for reading value from datastore
    val readLoginStatus: Flow<Boolean> = context.dataStore.data
        .catch { exception ->
            // dataStore.data throws an IOException when an error is encountered when reading data
            if (exception is IOException) {
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }
        .map {preferences ->
            preferences[PrefDataStoreConstant.ISLOGIN] ?: false
        }
}