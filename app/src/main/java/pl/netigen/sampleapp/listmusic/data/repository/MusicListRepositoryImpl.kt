package pl.netigen.sampleapp.listmusic.data.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import pl.netigen.sampleapp.core.api.AudioApi
import pl.netigen.sampleapp.core.api.AudioResponse
import pl.netigen.sampleapp.core.base.networkBoundResource
import pl.netigen.sampleapp.listmusic.data.local.dao.AudioDao
import pl.netigen.sampleapp.listmusic.data.local.model.AudioCached
import pl.netigen.sampleapp.listmusic.domain.MusicListRepository
import pl.netigen.sampleapp.listmusic.domain.model.Audio
import javax.inject.Inject


class MusicListRepositoryImpl @Inject constructor(private val audioDao: AudioDao, private val audioApi: AudioApi) : MusicListRepository {

    override fun getLikeMusic(): Flow<List<Audio>> = audioDao.getLikeAudio().map { audio -> audio.map { it.toAudio() } }

    override fun getMusicFromLocal() = audioDao.getAudio().map { audio -> audio.map { it.toAudio() } }

    override suspend fun getMusicFromRemote() = networkBoundResource(
        query = { getMusicFromLocal() },
        fetch = { audioApi.getMusics() },
        saveFetchResult = { list -> saveMusicToLocal(list) },
        emitValue = false,
        shouldFetch = { true },
        coroutineDispatcher = Dispatchers.IO
    )

    override suspend fun saveMusicToLocal(list: AudioResponse) {
        list.map { it.toAudio() }.map { AudioCached(it) }.toTypedArray().let {
            audioDao.saveAudio(*it)
        }
    }

    override suspend fun setLikeMusic(id: Int) {
        val audioById = audioDao.getAudioById(id)
        audioDao.updateAudio(audioById.copy(isLike = !audioById.isLike))
    }

}