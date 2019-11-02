package com.madonnaapps.wodnotify.data.local

import com.madonnaapps.wodnotify.data.local.daos.WodDao
import com.madonnaapps.wodnotify.data.local.entities.WodEntity
import kotlinx.coroutines.flow.Flow

interface WodLocalDataSource {

    suspend fun getAllWodsAsFlow(): Flow<List<WodEntity>>

    suspend fun getAllWods(): List<WodEntity>

    suspend fun getWodById(id: String): WodEntity

    suspend fun insertWods(wods: List<WodEntity>)

}

class WodLocalDataSourceImpl constructor(
    private val wodDao: WodDao
) : WodLocalDataSource {

    override suspend fun getAllWodsAsFlow(): Flow<List<WodEntity>> = wodDao.getAllWodsAsFlow()

    override suspend fun getAllWods(): List<WodEntity> = wodDao.getAllWods()

    override suspend fun getWodById(id: String): WodEntity = wodDao.getWodById(id)

    override suspend fun insertWods(wods: List<WodEntity>) = wodDao.insertWods(wods)

}