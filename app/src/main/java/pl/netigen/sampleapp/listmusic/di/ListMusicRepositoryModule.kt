package pl.netigen.sampleapp.listmusic.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.scopes.ActivityRetainedScoped
import pl.netigen.sampleapp.listmusic.data.repository.MusicListRepositoryImpl
import pl.netigen.sampleapp.listmusic.domain.MusicListRepository


@InstallIn(ActivityRetainedComponent::class)
@Module
abstract class MusicListRepositoryModule {
    @ActivityRetainedScoped
    @Binds
    abstract fun bindMusicListRepositoryInterface(repository: MusicListRepositoryImpl): MusicListRepository
}