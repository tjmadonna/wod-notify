package com.madonnaapps.wodnotify.data

import android.util.Log
import com.madonnaapps.wodnotify.common.extensions.logTag
import com.madonnaapps.wodnotify.common.types.Result
import com.madonnaapps.wodnotify.data.local.WodLocalDataSource
import com.madonnaapps.wodnotify.data.local.entities.WodEntity
import com.madonnaapps.wodnotify.data.preferences.SharedPreferencesDataSource
import com.madonnaapps.wodnotify.data.remote.WodRemoteDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton

interface WodRepository {

    suspend fun getAllWods(): Flow<List<WodEntity>>

    suspend fun getWodById(id: String): WodEntity

}

@Singleton
class WodRepositoryImpl @Inject constructor(
    private val wodLocalDataSource: WodLocalDataSource,
    private val wodRemoteDataSource: WodRemoteDataSource,
    private val sharedPreferencesDataSource: SharedPreferencesDataSource
) : WodRepository {

    override suspend fun getAllWods(): Flow<List<WodEntity>> = withContext(Dispatchers.IO) {

        val currentTime = Date().time

        if (currentTime - sharedPreferencesDataSource.getLastWodSync() > 900000) {
            // Only check if it's been 15 minutes since the last sync

            when (val result = wodRemoteDataSource.getWods()) {
                is Result.Success -> {
                    wodLocalDataSource.insertWods(result.data)
                    sharedPreferencesDataSource.saveLastWodSync(currentTime)
                }
                is Result.Failure -> {
                    Log.e(logTag, result.throwable.localizedMessage, result.throwable)
                }
            }
        }

        return@withContext wodLocalDataSource.getAllWodsAsFlow()
    }

    override suspend fun getWodById(id: String): WodEntity = withContext(Dispatchers.IO) {
        return@withContext wodLocalDataSource.getWodById(id)
    }

}