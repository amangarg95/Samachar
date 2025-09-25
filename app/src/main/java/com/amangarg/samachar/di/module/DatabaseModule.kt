package com.amangarg.samachar.di.module

import android.content.Context
import androidx.room.Room
import com.amangarg.samachar.data.local.database.DatabaseService
import com.amangarg.samachar.data.local.database.SamacharDatabase
import com.amangarg.samachar.data.local.database.SamacharDatabaseService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): SamacharDatabase {
        return Room.databaseBuilder(
            context,
            SamacharDatabase::class.java,
            SamacharDatabase.DATABASE_NAME
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideDatabaseService(database: SamacharDatabase): DatabaseService {
        return SamacharDatabaseService(database)
    }
}