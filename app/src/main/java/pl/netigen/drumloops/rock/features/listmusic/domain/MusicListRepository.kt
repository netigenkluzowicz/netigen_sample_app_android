package pl.netigen.drumloops.rock.features.listmusic.domain

import kotlinx.coroutines.flow.Flow
import pl.netigen.drumloops.rock.features.listmusic.domain.model.Audio
import javax.inject.Singleton


interface MusicListRepository {

    fun getLikeMusic(): Flow<List<Audio>>
    suspend fun getMusic(): List<Audio>
    suspend fun getMusicFromLocal(): List<Audio>
    suspend fun getMusicFromRemote(): List<Audio>
    suspend fun saveMusicToLocal(list: List<Audio>)
    suspend fun setLikeMusic(id: Int)
}