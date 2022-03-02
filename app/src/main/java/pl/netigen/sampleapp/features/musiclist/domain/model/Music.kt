package pl.netigen.sampleapp.features.musiclist.domain.model

data class Music(
    val baseBpm: Int,
    val genre: String,
    val genreColor: String,
    val loopId: Int,
    val measure: String,
    val name: String,
    val paymentType: String,
    val tempo: String,
    val isBuy: Boolean,
    val isLike: Boolean,
)
