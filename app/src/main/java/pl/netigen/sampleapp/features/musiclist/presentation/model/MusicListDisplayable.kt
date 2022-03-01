package pl.netigen.sampleapp.features.musiclist.presentation.model

import pl.netigen.sampleapp.core.data.State

data class MusicListDisplayable(
    val isLoading: Boolean = false,
    val allMusic: List<MusicDisplayable> = emptyList(),
    val likeMusic: List<MusicDisplayable> = emptyList(),
    val error: String = "",
    val isUserPremium: Boolean = false,
) : State
