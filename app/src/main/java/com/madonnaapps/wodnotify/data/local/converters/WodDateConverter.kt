package com.madonnaapps.wodnotify.data.local.converters

import androidx.room.TypeConverter
import java.util.*

object WodDateConverter {

    @TypeConverter
    @JvmStatic
    fun longToDate(longDate: Long) = Date(longDate)

    @TypeConverter
    @JvmStatic
    fun dateToLong(date: Date) = date.time

}