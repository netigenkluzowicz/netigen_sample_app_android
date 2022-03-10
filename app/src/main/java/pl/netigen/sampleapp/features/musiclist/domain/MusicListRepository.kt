package pl.netigen.sampleapp.features.musiclist.domain

import kotlinx.coroutines.flow.Flow
import pl.netigen.sampleapp.core.base.Resource
import pl.netigen.sampleapp.features.musiclist.domain.model.Music

interface MusicListRepository {
    fun getLikeMusic(): Flow<List<Music>>
    suspend fun getMusic(): Flow<Resource<List<Music>>>
    suspend fun setLikeMusic(id: Int)
    suspend fun setBuyAllMusic(isBuy: Boolean)
    suspend fun setBuyMusic(params: Int)
}
