package pl.netigen.sampleapp.features.musiclist.data.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import pl.netigen.sampleapp.core.api.MusicApi
import pl.netigen.sampleapp.core.api.MusicResponse
import pl.netigen.sampleapp.core.base.Resource
import pl.netigen.sampleapp.core.base.networkLocalBoundResource
import pl.netigen.sampleapp.core.network.NetworkStateProvider
import pl.netigen.sampleapp.features.musiclist.data.local.dao.MusicDao
import pl.netigen.sampleapp.features.musiclist.data.local.model.MusicCached
import pl.netigen.sampleapp.features.musiclist.data.local.model.MusicCached.Companion.PREMIUM
import pl.netigen.sampleapp.features.musiclist.domain.MusicListRepository
import pl.netigen.sampleapp.features.musiclist.domain.model.Music
import javax.inject.Inject

class MusicListRepositoryImpl @Inject constructor(
    private val musicDao: MusicDao,
    private val musicApi: MusicApi,
    private val networkStateProvider: NetworkStateProvider,
) : MusicListRepository {

    override fun getLikeMusic(): Flow<List<Music>> = musicDao.getLikeMusic().map { audio -> audio.map { it.toMusic() } }

    override suspend fun getMusic(): Flow<Resource<List<Music>>> {
        val networkBoundFlow = networkLocalBoundResource(
            fetchFromLocal = { getMusicFromLocal() },
            shouldFetchFromRemote = { networkStateProvider.isNetworkAvailable() },
            fetchFromRemote = musicApi::getMusics,
            saveFetchResult = this::saveMusicToLocal,
        )
        return networkBoundFlow.flowOn(Dispatchers.IO)
    }

    private fun getMusicFromLocal(): Flow<List<Music>> = musicDao.getMusic().map { audio -> audio.map { it.toMusic() } }

    private fun saveMusicToLocal(list: MusicResponse) {
        list.map { it.toAudio() }.map { MusicCached(it) }.toTypedArray().let {
            musicDao.saveMusic(*it)
        }
    }

    override suspend fun setLikeMusic(id: Int) {
        val audioById = musicDao.getMusicById(id)
        musicDao.updateMusic(audioById.copy(isLike = !audioById.isLike))
    }

    override suspend fun setBuyAllMusic(isBuy: Boolean) {
        val listMusic = musicDao.getListMusic()
        listMusic.forEach { music ->
            if (music.paymentType == PREMIUM) {
                musicDao.updateMusic(music.copy(isBuy = isBuy))
            }
        }
    }

    override suspend fun setBuyMusic(id: Int) {
        val music = musicDao.getMusicById(id)
        musicDao.updateMusic(music.copy(isBuy = true))
    }
}
