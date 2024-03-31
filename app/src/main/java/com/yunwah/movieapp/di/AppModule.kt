package com.yunwah.movieapp.di

import android.app.Application
import androidx.room.Room
import com.yunwah.movieapp.data.local.MoviesDao
import com.yunwah.movieapp.data.local.MoviesDatabase
import com.yunwah.movieapp.data.local.MoviesTypeConverter
import com.yunwah.movieapp.data.manager.LocalUserManagerImpl
import com.yunwah.movieapp.data.remote.MoviesApi
import com.yunwah.movieapp.data.remote.repository.MoviesRepositoryImpl
import com.yunwah.movieapp.domain.manager.LocalUserManager
import com.yunwah.movieapp.domain.respository.MoviesRepository
import com.yunwah.movieapp.domain.usecase.appEntry.AppEntryUseCases
import com.yunwah.movieapp.domain.usecase.appEntry.ReadAppEntry
import com.yunwah.movieapp.domain.usecase.appEntry.SaveAppEntry
import com.yunwah.movieapp.domain.usecase.movies.GetMovies
import com.yunwah.movieapp.domain.usecase.movies.MoviesUseCases
import com.yunwah.movieapp.utils.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideLocalUserManager(application: Application): LocalUserManager =
        LocalUserManagerImpl(application)

    @Provides
    @Singleton
    fun provideAppEntryUseCases(
        localUserManager: LocalUserManager
    ) = AppEntryUseCases(
        readAppEntry = ReadAppEntry(localUserManager),
        saveAppEntry = SaveAppEntry(localUserManager)
    )

    @Provides
    @Singleton
    fun provideApiInstance(): MoviesApi {
        return Retrofit
            .Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MoviesApi::class.java)
    }

    @Provides
    @Singleton
    fun provideMoviesRepository(
        moviesApi: MoviesApi,
        moviesDao: MoviesDao
    ): MoviesRepository {
        return MoviesRepositoryImpl(moviesApi, moviesDao)
    }

    @Provides
    @Singleton
    fun provideMoviesUseCases(
        moviesRepository: MoviesRepository
    ): MoviesUseCases {
        return MoviesUseCases(
            getMovies = GetMovies(moviesRepository)
        )
    }

    @Provides
    @Singleton
    fun provideMoviesDatabase(
        application: Application
    ): MoviesDatabase {
        return Room.databaseBuilder(
            context = application,
            klass = MoviesDatabase::class.java,
            name = "Movies"
        ).addTypeConverter(MoviesTypeConverter())
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideMoviesDao(
        moviesDatabase: MoviesDatabase
    ): MoviesDao = moviesDatabase.moviesDao
}