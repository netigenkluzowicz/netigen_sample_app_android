package pl.netigen.sampleapp.features.musiclist.data.local.dao

import androidx.room.*
import kotlinx.coroutines.flow.Flow
import pl.netigen.sampleapp.features.musiclist.data.local.model.MusicCached

@Dao
interface MusicDao {
    @Query("SELECT * FROM MusicCached")
    fun getMusic(): Flow<List<MusicCached>>

    @Query("SELECT * FROM MusicCached")
    suspend fun getListMusic(): List<MusicCached>

    @Update
    suspend fun updateMusic(music: MusicCached)

    @Query("SELECT * FROM MusicCached WHERE loopId = :id")
    suspend fun getMusicById(id: Int): MusicCached

    @Query("SELECT * FROM MusicCached WHERE isLike = 1")
    fun getLikeMusic(): Flow<List<MusicCached>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun saveMusic(vararg musicCached: MusicCached)
}
