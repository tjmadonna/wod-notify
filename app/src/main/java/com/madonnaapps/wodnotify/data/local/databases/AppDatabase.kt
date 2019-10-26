package com.madonnaapps.wodnotify.data.local.databases

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.madonnaapps.wodnotify.data.local.converters.WodDateConverter
import com.madonnaapps.wodnotify.data.local.daos.WodDao
import com.madonnaapps.wodnotify.data.local.entities.WodEntity

@Database(
    entities = [WodEntity::class],
    version = 1
)
@TypeConverters(WodDateConverter::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun wodDao(): WodDao

    companion object {

        @Volatile
        private var instance: AppDatabase? = null

        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: buildDatabase(context).also { instance = it }
        }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                "wodnotify.db"
            ).build()
    }

}