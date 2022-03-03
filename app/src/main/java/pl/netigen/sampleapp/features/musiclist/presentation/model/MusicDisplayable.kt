package pl.netigen.sampleapp.features.musiclist.presentation.model

import pl.netigen.sampleapp.core.data.Item
import pl.netigen.sampleapp.features.musiclist.domain.model.Music

data class MusicDisplayable(
    override val id: Int,
    val genre: String,
    val name: String,
    val isBuy: Boolean,
    val isLike: Boolean,
) : Item {
    constructor(music: Music) : this(
        music.loopId,
        music.genre,
        music.name,
        music.isBuy,
        music.isLike,
    )
}
