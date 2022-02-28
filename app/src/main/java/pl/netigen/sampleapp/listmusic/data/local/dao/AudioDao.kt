package pl.netigen.sampleapp.listmusic.data.local.dao

import androidx.room.*
import kotlinx.coroutines.flow.Flow
import pl.netigen.sampleapp.listmusic.data.local.model.AudioCached

@Dao
interface AudioDao {
    @Query("SELECT * FROM AudioCached")
    fun getAudio(): Flow<List<AudioCached>>

    @Update
    suspend fun updateAudio(audio: AudioCached)

    @Query("SELECT * FROM AudioCached WHERE loopId = :id")
    suspend fun getAudioById(id: Int): AudioCached

    @Query("SELECT * FROM AudioCached WHERE isLike = 1")
    fun getLikeAudio(): Flow<List<AudioCached>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun saveAudio(vararg audioCached: AudioCached)


}