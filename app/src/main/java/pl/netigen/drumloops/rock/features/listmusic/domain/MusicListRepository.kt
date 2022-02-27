package pl.netigen.drumloops.rock.features.listmusic.domain

import kotlinx.coroutines.flow.Flow
import pl.netigen.drumloops.rock.core.api.AudioResponse
import pl.netigen.drumloops.rock.core.base.Resource
import pl.netigen.drumloops.rock.features.listmusic.domain.model.Audio


interface MusicListRepository {
    fun getLikeMusic(): Flow<List<Audio>>
    fun getMusicFromLocal(): Flow<List<Audio>>
    suspend fun getMusicFromRemote(): Flow<Resource<List<Audio>>>
    suspend fun saveMusicToLocal(list: AudioResponse)
    suspend fun setLikeMusic(id: Int)
}