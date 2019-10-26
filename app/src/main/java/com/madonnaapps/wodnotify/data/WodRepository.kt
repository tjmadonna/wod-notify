package com.madonnaapps.wodnotify.data

import com.madonnaapps.wodnotify.data.local.WodLocalDataSource
import com.madonnaapps.wodnotify.data.local.entities.WodEntity
import com.madonnaapps.wodnotify.data.remote.WodRemoteDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

interface WodRepository {

    suspend fun getAllWods(): Flow<List<WodEntity>>

    suspend fun getWodById(id: String): WodEntity

}

class WodRepositoryImpl constructor(
    private val wodLocalDataSource: WodLocalDataSource,
    private val wodRemoteDataSource: WodRemoteDataSource
) : WodRepository {

    override suspend fun getAllWods(): Flow<List<WodEntity>> = withContext(Dispatchers.IO) {
        val remoteItems = wodRemoteDataSource.getWods()
        wodLocalDataSource.insertWods(remoteItems)
        return@withContext wodLocalDataSource.getAllWods()
    }

    override suspend fun getWodById(id: String): WodEntity = withContext(Dispatchers.IO) {
        return@withContext wodLocalDataSource.getWodById(id)
    }

}