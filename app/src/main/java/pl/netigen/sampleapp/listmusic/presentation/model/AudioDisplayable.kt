package pl.netigen.sampleapp.listmusic.presentation.model

import pl.netigen.sampleapp.core.data.Item
import pl.netigen.sampleapp.listmusic.domain.model.Audio

data class AudioDisplayable(
    override val id: Int,
    val genre: String,
    val name: String,
    val isBuy: Boolean,
    val isLike: Boolean,
) : Item {
    constructor(audio: Audio) : this(
        audio.loopId,
        audio.genre,
        audio.name,
        audio.isBuy,
        audio.isLike
    )
}
