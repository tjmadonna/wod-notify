package com.madonnaapps.wodnotify.data.remote

import android.util.Log
import com.madonnaapps.wodnotify.data.local.entities.WodEntity
import com.madonnaapps.wodnotify.data.remote.mappers.WodNetworkResponseItemMapper
import com.madonnaapps.wodnotify.data.remote.services.WodRemoteService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

interface WodRemoteDataSource {

    suspend fun getWods(): List<WodEntity>

}

class WodRemoteDataSourceImpl constructor(
    private val wodRemoteService: WodRemoteService,
    private val mapper: WodNetworkResponseItemMapper
) : WodRemoteDataSource {

    override suspend fun getWods(): List<WodEntity> = withContext(Dispatchers.IO) {

        return@withContext try {
            wodRemoteService.getWods().items?.let {
                it.mapNotNull { item -> mapper.mapToWodEntity(item) }
            } ?: kotlin.run {
                emptyList<WodEntity>()
            }
        } catch (e: Exception) {
            Log.e("WodRemoteDataSource", e.localizedMessage, e)
            emptyList<WodEntity>()
        }
    }

}