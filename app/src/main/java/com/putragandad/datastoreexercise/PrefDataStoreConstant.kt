package com.putragandad.datastoreexercise

import androidx.datastore.preferences.core.booleanPreferencesKey

object PrefDataStoreConstant {
    const val PREFDATASTORE = "example_datastore"
    val ISLOGIN = booleanPreferencesKey("is_login")
}