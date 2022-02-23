package pl.netigen.drumloops.rock.features.listmusic.presentation.model

import pl.netigen.drumloops.rock.core.data.State

data class MusicListDisplayable(
    val isLoading: Boolean = false,
    val notes: List<AudioDisplayable> = emptyList(),
    val error: String? = null,
    val isUserPremium: Boolean? = null
) : State
