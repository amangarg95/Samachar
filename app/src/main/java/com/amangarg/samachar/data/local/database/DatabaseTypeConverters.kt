package com.amangarg.samachar.data.local.database

import androidx.room.TypeConverter
import java.util.Date

class DatabaseTypeConverters {
    
    @TypeConverter
    fun fromTimestamp(value: Long?): Date? = value?.let { Date(it) }
    
    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? = date?.time
}