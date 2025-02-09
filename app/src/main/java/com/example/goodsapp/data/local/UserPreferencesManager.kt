package com.example.goodsapp.data.local

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.example.goodsapp.domain.model.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class UserPreferencesManager @Inject constructor(
    private val dataStore: DataStore<Preferences>
) {

    private val tokenKey = stringPreferencesKey("auth_token")
    private val userIdKey = stringPreferencesKey("user_id")
    private val userEmailKey = stringPreferencesKey("user_email")
    private val usernameKey = stringPreferencesKey("username")

    suspend fun saveToken(token: String) {
        dataStore.edit { preferences ->
            preferences[tokenKey] = token
        }
    }

    suspend fun getToken(): String? {
        return dataStore.data.first()[tokenKey]
    }

    fun getTokenFlow(): Flow<String?> {
        return dataStore.data.map { preferences ->
            preferences[tokenKey]
        }
    }

    fun getUserFlow(): Flow<User?> {
        return dataStore.data.map { preferences ->
            val id = preferences[userIdKey]
            val email = preferences[userEmailKey]
            val username = preferences[usernameKey]

            User(
                id = id ?: "",
                email = email ?: "",
                username = username ?: "",
            )
        }
    }

    suspend fun saveUser(user: User) {
        dataStore.edit { preferences ->
            preferences[userIdKey] = user.id
            preferences[userEmailKey] = user.email
            preferences[usernameKey] = user.username
        }
    }

    suspend fun clearUser() {
        dataStore.edit { it.clear() }
    }

    fun isUserLoggedIn(): Flow<Boolean> {
        return dataStore.data.map { preferences ->
            preferences[tokenKey]?.isNotBlank() ?: false
        }
    }
}