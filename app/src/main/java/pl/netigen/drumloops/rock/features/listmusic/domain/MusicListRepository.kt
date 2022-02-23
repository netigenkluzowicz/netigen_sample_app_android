package pl.netigen.drumloops.rock.features.listmusic.domain

import pl.netigen.drumloops.rock.features.listmusic.domain.model.Audio

interface MusicListRepository {

    suspend fun getLikeMusic(): List<Audio>
    suspend fun getMusic(): List<Audio>
    suspend fun getMusicFromLocal(): List<Audio>
    suspend fun getMusicFromRemote(): List<Audio>
    suspend fun saveMusicToLocal(): List<Audio>
}