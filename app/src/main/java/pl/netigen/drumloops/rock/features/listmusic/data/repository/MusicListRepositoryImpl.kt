package pl.netigen.drumloops.rock.features.listmusic.data.repository

import pl.netigen.drumloops.rock.features.listmusic.domain.MusicListRepository
import pl.netigen.drumloops.rock.features.listmusic.domain.model.Audio
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MusicListRepositoryImpl @Inject constructor() : MusicListRepository {
    override suspend fun getLikeMusic(): List<Audio> {
        TODO("Not yet implemented")
    }

    override suspend fun getMusic(): List<Audio> {
        TODO("Not yet implemented")
    }

    override suspend fun getMusicFromLocal(): List<Audio> {
        TODO("Not yet implemented")
    }

    override suspend fun getMusicFromRemote(): List<Audio> {
        TODO("Not yet implemented")
    }

    override suspend fun saveMusicToLocal(): List<Audio> {
        TODO("Not yet implemented")
    }

}