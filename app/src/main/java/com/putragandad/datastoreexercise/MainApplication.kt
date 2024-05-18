package com.putragandad.datastoreexercise

import android.app.Application

class MainApplication : Application() {
    // initialize datastore in Application Context
    val dataStore by lazy {
        DataStorePreferences(this)
    }
}
