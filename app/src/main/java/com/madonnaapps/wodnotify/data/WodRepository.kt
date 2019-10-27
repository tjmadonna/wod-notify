package com.madonnaapps.wodnotify.data

import com.madonnaapps.wodnotify.data.local.WodLocalDataSource
import com.madonnaapps.wodnotify.data.local.entities.WodEntity
import com.madonnaapps.wodnotify.data.preferences.SharedPreferencesDataSource
import com.madonnaapps.wodnotify.data.remote.WodRemoteDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import java.util.*

interface WodRepository {

    suspend fun getAllWods(): Flow<List<WodEntity>>

    suspend fun getWodById(id: String): WodEntity

}

class WodRepositoryImpl constructor(
    private val wodLocalDataSource: WodLocalDataSource,
    private val wodRemoteDataSource: WodRemoteDataSource,
    private val sharedPreferencesDataSource: SharedPreferencesDataSource
) : WodRepository {

    override suspend fun getAllWods(): Flow<List<WodEntity>> = withContext(Dispatchers.IO) {

        val currentTime = Date().time

        if (currentTime - sharedPreferencesDataSource.getLastWodSync() > 900000) {
            // Only check if it's been 15 minutes since the last sync
            val remoteItems = wodRemoteDataSource.getWods()
            wodLocalDataSource.insertWods(remoteItems)
            sharedPreferencesDataSource.saveLastWodSync(currentTime)
        }

        return@withContext wodLocalDataSource.getAllWods()
    }

    override suspend fun getWodById(id: String): WodEntity = withContext(Dispatchers.IO) {
        return@withContext wodLocalDataSource.getWodById(id)
    }

}