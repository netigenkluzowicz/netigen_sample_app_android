package pl.netigen.sampleapp.listmusic.domain

import kotlinx.coroutines.flow.Flow
import pl.netigen.sampleapp.core.api.AudioResponse
import pl.netigen.sampleapp.core.base.Resource
import pl.netigen.sampleapp.listmusic.domain.model.Audio


interface MusicListRepository {
    fun getLikeMusic(): Flow<List<Audio>>
    fun getMusicFromLocal(): Flow<List<Audio>>
    suspend fun getMusicFromRemote(): Flow<Resource<List<Audio>>>
    suspend fun saveMusicToLocal(list: AudioResponse)
    suspend fun setLikeMusic(id: Int)
}