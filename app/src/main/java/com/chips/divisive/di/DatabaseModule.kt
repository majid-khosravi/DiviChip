package com.chips.divisive.di

import android.content.Context
import androidx.room.Room
import com.chips.divisive.db.AppDatabase
import com.chips.divisive.db.ProfilesDAO
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appContext: Context): AppDatabase {
        return Room.databaseBuilder(
            appContext,
            AppDatabase::class.java,
            name = "DiviChip-DB"
        ).allowMainThreadQueries().build()
    }


    @Provides
    @Singleton
    fun provideProfileDao(appDatabase: AppDatabase): ProfilesDAO {
        return appDatabase.profilesDAO()
    }

}