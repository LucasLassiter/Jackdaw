package com.lucasanimalfacts.jackdaw.di

import com.lucasanimalfacts.jackdaw.feature_mainapp.data.repository.SubsonicRepositoryImpl
import com.lucasanimalfacts.jackdaw.feature_mainapp.domain.repository.SubsonicRepository
import com.lucasanimalfacts.jackdaw.feature_mainapp.domain.use_case.GetAlbumArt
import com.lucasanimalfacts.jackdaw.feature_mainapp.domain.use_case.GetPlaylist
import com.lucasanimalfacts.jackdaw.feature_mainapp.domain.use_case.GetPlaylists
import com.lucasanimalfacts.jackdaw.feature_mainapp.domain.use_case.GetRandomSongs
import com.lucasanimalfacts.jackdaw.feature_mainapp.domain.use_case.GetStarred
import com.lucasanimalfacts.jackdaw.feature_mainapp.domain.use_case.UseCaseWrapper
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
            getAlbumArt = GetAlbumArt(repository),
            getPlaylists = GetPlaylists(repository),
            getPlaylist = GetPlaylist(repository)
        )
    }
}