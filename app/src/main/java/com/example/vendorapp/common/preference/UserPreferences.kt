package com.example.vendorapp.common.preference

import android.content.Context
import androidx.annotation.Keep
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "user_data_store")

@Keep
class UserPreferences @Inject constructor(@ApplicationContext context: Context) {

    private val appContext = context.applicationContext

    @Keep
    companion object {
        private val ACCESS_TOKEN = stringPreferencesKey("key_access_token")
        private val REFRESH_TOKEN = stringPreferencesKey("key_refresh_token")
        private val RECENT_LOCATIONS = stringPreferencesKey("recent_locations")
        private val RECENT_SEARCHES = stringPreferencesKey("recent_searches")
        private val SAVE_LOCATION = stringPreferencesKey("save_location")
    }

    /*
    * For saving access token and refresh token
    * Param type: String
    */
    suspend fun saveAccessAndRefreshToken(accessToken: String, refreshToken: String) {
        appContext.dataStore.edit { preferences ->
            preferences[ACCESS_TOKEN] = accessToken
            preferences[REFRESH_TOKEN] = refreshToken
        }
    }

    suspend fun clear() {
        appContext.dataStore.edit { preferences ->
            preferences.clear()
        }
    }

    val getAccessToken: Flow<String?>
        get() = appContext.dataStore.data.map { preferences ->
            preferences[ACCESS_TOKEN]
        }

    val getRefreshToken: Flow<String?>
        get() = appContext.dataStore.data.map { preferences ->
            preferences[REFRESH_TOKEN]
        }

    val recentLocations: Flow<String?>
        get() = appContext.dataStore.data.map { preferences ->
            preferences[RECENT_LOCATIONS]
        }

    val recentSearches: Flow<String?>
        get() = appContext.dataStore.data.map { preferences ->
            preferences[RECENT_SEARCHES]
        }

    suspend fun saveLocation(location:String){
        appContext.dataStore.edit {
            it[SAVE_LOCATION] = location
        }
    }

    suspend fun saveRecentLocation(location:String){
        appContext.dataStore.edit {
            it[RECENT_LOCATIONS] = location
        }
    }
}