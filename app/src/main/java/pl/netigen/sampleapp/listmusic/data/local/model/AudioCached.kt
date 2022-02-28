package pl.netigen.sampleapp.listmusic.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import pl.netigen.sampleapp.listmusic.domain.model.Audio


@Entity
data class AudioCached(
    @PrimaryKey
    val loopId: Int,
    val baseBpm: Int,
    val genre: String,
    val genreColor: String,
    val measure: String,
    val name: String,
    val paymentType: String,
    val tempo: String,
    val isBuy: Boolean,
    val isLike: Boolean,
) {
    constructor(audio: Audio) : this(
        audio.loopId,
        audio.baseBpm,
        audio.genre,
        audio.genreColor,
        audio.measure,
        audio.name,
        audio.paymentType,
        audio.tempo,
        audio.isBuy,
        audio.isLike
    )

    companion object

    fun toAudio() = Audio(
        baseBpm, genre, genreColor, loopId, measure, name, paymentType, tempo, isBuy, isLike
    )

}