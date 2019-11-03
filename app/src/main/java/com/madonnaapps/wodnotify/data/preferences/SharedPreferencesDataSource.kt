package com.madonnaapps.wodnotify.data.preferences

import android.content.SharedPreferences
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

interface SharedPreferencesDataSource {

    suspend fun getLastWodSync(): Long

    suspend fun saveLastWodSync(lastSync: Long)

}

@Singleton
class SharedPreferencesDataSourceImpl @Inject constructor(
    private val sharedPreferences: SharedPreferences
) : SharedPreferencesDataSource {

    private val lastWodSyncKey = "LastWodSyncPrefKey"

    override suspend fun getLastWodSync(): Long = withContext(Dispatchers.IO) {
        return@withContext sharedPreferences.getLong(lastWodSyncKey, 0)
    }

    override suspend fun saveLastWodSync(lastSync: Long) = withContext(Dispatchers.IO) {
        sharedPreferences.edit()
            .putLong(lastWodSyncKey, lastSync)
            .apply()
    }
}