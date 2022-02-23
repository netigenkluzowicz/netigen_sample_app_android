package pl.netigen.drumloops.rock.features.listmusic.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.scopes.ActivityRetainedScoped
import pl.netigen.drumloops.rock.features.listmusic.data.repository.MusicListRepositoryImpl
import pl.netigen.drumloops.rock.features.listmusic.domain.MusicListRepository


@InstallIn(ActivityComponent::class)
@Module
abstract class MusicListRepositoryModule {
    @ActivityRetainedScoped
    @Binds
    abstract fun bindMusicListRepositoryInterface(repository: MusicListRepositoryImpl): MusicListRepository
}