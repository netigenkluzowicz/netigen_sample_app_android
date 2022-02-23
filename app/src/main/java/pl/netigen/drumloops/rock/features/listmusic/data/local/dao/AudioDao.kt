package pl.netigen.drumloops.rock.features.listmusic.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import pl.netigen.drumloops.rock.features.listmusic.data.local.model.AudioCached

@Dao
interface AudioDao {
    @Query("SELECT * FROM AudioCached")
    suspend fun getAudio(): List<AudioCached>

    @Query("SELECT * FROM AudioCached WHERE isLike = 1")
    suspend fun getLikeAudio(): List<AudioCached>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun saveAudio(vararg audioCached: AudioCached)
}