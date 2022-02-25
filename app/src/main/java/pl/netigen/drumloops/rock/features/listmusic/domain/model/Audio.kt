package pl.netigen.drumloops.rock.features.listmusic.domain.model


data class Audio(
    val baseBpm: Int,
    val genre: String,
    val genreColor: String,
    val loopId: Int,
    val measure: String,
    val name: String,
    val paymentType: String,
    val tempo: String,
    val isBuy: Boolean,
    val isLike: Boolean
)