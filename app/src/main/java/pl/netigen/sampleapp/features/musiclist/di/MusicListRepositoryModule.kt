package pl.netigen.sampleapp.features.musiclist.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.scopes.ActivityRetainedScoped
import pl.netigen.sampleapp.features.musiclist.data.repository.MusicListRepositoryImpl
import pl.netigen.sampleapp.features.musiclist.domain.MusicListRepository

@InstallIn(ActivityRetainedComponent::class)
@Module
abstract class MusicListRepositoryModule {
    @ActivityRetainedScoped
    @Binds
    abstract fun bindMusicListRepositoryInterface(repository: MusicListRepositoryImpl): MusicListRepository
}
