package com.example.rijksmuseumapp.di

import android.content.Context
import androidx.room.Room
import com.example.rijksmuseumapp.ArtObjectRepository
import com.example.rijksmuseumapp.database.AppDatabase
import com.example.rijksmuseumapp.database.ArtObjectDao
import com.example.rijksmuseumapp.network.ArtObjectService
import com.example.rijksmuseumapp.ui.main.ArtObjectViewModel
import com.example.rijksmuseumapp.artdetails.ArtDetailsViewModel

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext appContext: Context): AppDatabase {
        return Room.databaseBuilder(
            appContext,
            AppDatabase::class.java,
            "art_db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideArtObjectDao(database: AppDatabase): ArtObjectDao {
        return database.artObjectDao()
    }

    @Provides
    @Singleton
    fun provideRepository(dao: ArtObjectDao, service: ArtObjectService, database: AppDatabase) =
        ArtObjectRepository (dao, service, database)

    @Provides
    fun provideMainViewModel(artObjectRepository: ArtObjectRepository): ArtObjectViewModel {
        return ArtObjectViewModel(artObjectRepository)
    }

    @Provides
    fun provideArtDetailsViewModel(artObjectRepository: ArtObjectRepository): ArtDetailsViewModel {
        return ArtDetailsViewModel(artObjectRepository)
    }
}