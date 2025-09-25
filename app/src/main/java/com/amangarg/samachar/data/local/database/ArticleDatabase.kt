package com.amangarg.samachar.data.local.database

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import android.content.Context
import com.amangarg.samachar.data.local.database.dao.ArticleDao
import com.amangarg.samachar.data.local.database.entity.ArticleEntity

@Database(
    entities = [ArticleEntity::class],
    version = 2,
    exportSchema = true
)
@TypeConverters(DatabaseTypeConverters::class)
abstract class SamacharDatabase : RoomDatabase() {

    abstract fun articleDao(): ArticleDao

    companion object {
        const val DATABASE_NAME = "samachar_database"

        @Volatile
        private var INSTANCE: SamacharDatabase? = null

        fun getDatabase(context: Context): SamacharDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    SamacharDatabase::class.java,
                    DATABASE_NAME
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}