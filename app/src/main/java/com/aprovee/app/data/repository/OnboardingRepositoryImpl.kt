package com.aprovee.app.data.repository

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.core.IOException
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.preferencesDataStore
import com.aprovee.app.domain.repository.OnboardingRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map

val Context.onboardingDataStore: DataStore<Preferences> by preferencesDataStore(
    name = "onboarding"
)

private val hasCompletedOnboardingKey = booleanPreferencesKey("completed")

class OnboardingRepositoryImpl(
    private val dataStore: DataStore<Preferences>
) : OnboardingRepository {
    override fun hasCompletedOnboarding(): Flow<Boolean> =
        dataStore.data
            .catch { exception ->
                if (exception is IOException) {
                    emit(emptyPreferences())
                } else {
                    throw exception
                }
            }
            .map { preferences ->
                preferences[hasCompletedOnboardingKey] ?: false
            }

    override suspend fun markCompleted() {
        dataStore.edit { preferences ->
            preferences[hasCompletedOnboardingKey] = true
        }
    }
}