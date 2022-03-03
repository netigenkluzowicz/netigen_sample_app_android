package pl.netigen.sampleapp.features.musiclist.domain

import kotlinx.coroutines.flow.Flow
import pl.netigen.sampleapp.core.api.MusicResponse
import pl.netigen.sampleapp.core.base.Resource
import pl.netigen.sampleapp.features.musiclist.domain.model.Music

interface MusicListRepository {
    fun getLikeMusic(): Flow<List<Music>>
    fun getMusicFromLocal(): Flow<List<Music>>
    suspend fun getMusicFromRemote(): Flow<Resource<List<Music>>>
    suspend fun saveMusicToLocal(list: MusicResponse)
    suspend fun setLikeMusic(id: Int)
    suspend fun setBuyMusic(isBuy: Boolean)
}
