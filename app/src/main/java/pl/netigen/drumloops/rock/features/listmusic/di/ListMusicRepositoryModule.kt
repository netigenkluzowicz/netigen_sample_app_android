package pl.netigen.drumloops.rock.features.listmusic.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.scopes.ActivityRetainedScoped
import pl.netigen.drumloops.rock.features.listmusic.data.repository.MusicListRepositoryImpl
import pl.netigen.drumloops.rock.features.listmusic.domain.MusicListRepository


@InstallIn(ActivityRetainedComponent::class)
@Module
abstract class MusicListRepositoryModule {
    @ActivityRetainedScoped
    @Binds
    abstract fun bindMusicListRepositoryInterface(repository: MusicListRepositoryImpl): MusicListRepository
}