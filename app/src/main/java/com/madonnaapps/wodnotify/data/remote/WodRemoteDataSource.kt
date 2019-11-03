package com.madonnaapps.wodnotify.data.remote

import com.madonnaapps.wodnotify.common.types.Result
import com.madonnaapps.wodnotify.data.local.entities.WodEntity
import com.madonnaapps.wodnotify.data.remote.mappers.WodNetworkResponseItemMapper
import com.madonnaapps.wodnotify.data.remote.services.WodRemoteService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

interface WodRemoteDataSource {

    suspend fun getWods(): Result<List<WodEntity>>

}

@Singleton
class WodRemoteDataSourceImpl @Inject constructor(
    private val wodRemoteService: WodRemoteService,
    private val mapper: WodNetworkResponseItemMapper
) : WodRemoteDataSource {

    override suspend fun getWods(): Result<List<WodEntity>> = withContext(Dispatchers.IO) {

        return@withContext try {
            wodRemoteService.getWods().items?.let {
                Result.Success(it.mapNotNull { item -> mapper.mapToWodEntity(item) })
            } ?: kotlin.run {
                Result.Failure(Exception("Remote data source returned no wods"))
            }
        } catch (e: Exception) {
            Result.Failure(Exception("Remote data source returned no wods"))
        }
    }

}