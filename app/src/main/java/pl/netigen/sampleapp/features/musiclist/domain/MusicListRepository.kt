package pl.netigen.sampleapp.features.musiclist.domain

import kotlinx.coroutines.flow.Flow
import pl.netigen.sampleapp.core.api.MusicResponse
import pl.netigen.sampleapp.core.base.Resource
import pl.netigen.sampleapp.features.musiclist.domain.model.Music

interface MusicListRepository {
    fun getLikeMusic(): Flow<List<Music>>
    fun getMusic(): Flow<List<Music>>
    fun getMusicFromRemote(): Flow<Resource<List<Music>>>
    suspend fun saveMusicToLocal(list: MusicResponse)
    suspend fun setLikeMusic(id: Int)
    suspend fun setBuyAllMusic(params: Boolean)
    suspend fun setBuyMusic(params: Int)
}
