package pl.netigen.drumloops.rock.features.listmusic.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import pl.netigen.drumloops.rock.core.api.AudioApi
import pl.netigen.drumloops.rock.features.listmusic.data.local.dao.AudioDao
import pl.netigen.drumloops.rock.features.listmusic.data.local.model.AudioCached
import pl.netigen.drumloops.rock.features.listmusic.domain.MusicListRepository
import pl.netigen.drumloops.rock.features.listmusic.domain.model.Audio
import javax.inject.Inject


class MusicListRepositoryImpl @Inject constructor(private val audioDao: AudioDao, private val audioApi: AudioApi) : MusicListRepository {
    override fun getLikeMusic(): Flow<List<Audio>> =
        audioDao.getLikeAudio().map { audio -> audio.map { it.toAudio() } }


    override suspend fun getMusic(): List<Audio> {
        return getMusicFromRemote().also { saveMusicToLocal(it) }
    }

    override suspend fun getMusicFromLocal(): List<Audio> {
        return listOf()
    }

    override suspend fun getMusicFromRemote(): List<Audio> {
        val list = audioApi.getMusics()
        return list.map { it.toAudio() }
    }

    override suspend fun saveMusicToLocal(list: List<Audio>) {
        list.map { AudioCached(it) }.toTypedArray().let {
            audioDao.saveAudio(*it)
        }
    }

    override suspend fun setLikeMusic(id: Int) {
        val audioById = audioDao.getAudioById(id)
        audioDao.updateAudio(audioById.copy(isLike = !audioById.isLike))
    }

}