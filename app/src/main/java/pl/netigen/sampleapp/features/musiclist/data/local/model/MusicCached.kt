package pl.netigen.sampleapp.features.musiclist.data.local.model

import androidx.annotation.StringDef
import androidx.room.Entity
import androidx.room.PrimaryKey
import pl.netigen.sampleapp.features.musiclist.domain.model.Music

@Entity
data class MusicCached(
    @PrimaryKey
    val loopId: Int,
    val baseBpm: Int,
    val genre: String,
    val genreColor: String,
    val measure: String,
    val name: String,
    @PaymentType
    val paymentType: String,
    val tempo: String,
    val isBuy: Boolean,
    val isLike: Boolean,
) {
    constructor(music: Music) : this(
        music.loopId,
        music.baseBpm,
        music.genre,
        music.genreColor,
        music.measure,
        music.name,
        music.paymentType,
        music.tempo,
        music.isBuy,
        music.isLike
    )

    companion object {
        const val PREMIUM = "PREMIUM"
        const val FREE = "FREE"
    }

    @Retention(AnnotationRetention.SOURCE)
    @StringDef(PREMIUM, FREE)
    annotation class PaymentType

    fun toMusic() = Music(
        baseBpm, genre, genreColor, loopId, measure, name, paymentType, tempo, isBuy, isLike
    )
}
