package com.madonnaapps.wodnotify.data.local.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.madonnaapps.wodnotify.data.local.entities.WodEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface WodDao {

    @Query("SELECT * FROM wods ORDER BY date DESC")
    fun getAllWods(): Flow<List<WodEntity>>

    @Query("SELECT * FROM wods WHERE id = :id")
    suspend fun getWodById(id: String): WodEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWods(wods: List<WodEntity>)

}