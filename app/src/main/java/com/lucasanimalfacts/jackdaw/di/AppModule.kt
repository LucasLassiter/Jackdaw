package com.lucasanimalfacts.jackdaw.di

import android.app.Application
import androidx.room.Room
import com.lucasanimalfacts.jackdaw.data.repository.SubsonicRepositoryImpl
import com.lucasanimalfacts.jackdaw.domain.repository.SubsonicRepository
import com.lucasanimalfacts.jackdaw.domain.use_case.GetAlbumArt
import com.lucasanimalfacts.jackdaw.domain.use_case.GetRandomSongs
import com.lucasanimalfacts.jackdaw.domain.use_case.GetStarred
import com.lucasanimalfacts.jackdaw.domain.use_case.UseCaseWrapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideSubsonicRepository(): SubsonicRepository {
        return SubsonicRepositoryImpl()
    }

    @Provides
    @Singleton
    fun provideSubsonicUseCases(repository: SubsonicRepository): UseCaseWrapper {
        return UseCaseWrapper(
            getRandomSongs = GetRandomSongs(repository),
            getStarred = GetStarred(repository),
            getAlbumArt = GetAlbumArt(repository)
        )
    }
}