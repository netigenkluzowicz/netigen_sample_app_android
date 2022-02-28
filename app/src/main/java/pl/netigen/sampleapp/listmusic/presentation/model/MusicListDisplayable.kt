package pl.netigen.sampleapp.listmusic.presentation.model

import pl.netigen.sampleapp.core.data.State

data class MusicListDisplayable(
    val isLoading: Boolean = false,
    val allAudio: List<AudioDisplayable> = emptyList(),
    val likeAudio: List<AudioDisplayable> = emptyList(),
    val error: String? = null,
    val isUserPremium: Boolean? = null,
) : State
