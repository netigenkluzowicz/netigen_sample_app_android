package pl.netigen.sampleapp.features.musiclist.presentation.model

import pl.netigen.sampleapp.core.data.ViewEvent
import pl.netigen.sampleapp.core.data.ViewState

class MusicListContract {
    data class MusicListState(
        val isLoading: Boolean = false,
        val allMusic: List<MusicDisplayable> = emptyList(),
        val likeMusic: List<MusicDisplayable> = emptyList(),
        val error: String = "",
        val isUserPremium: Boolean = false,
    ) : ViewState

    sealed class MusicListEvent : ViewEvent {
        data class LikeMusicClicked(val music: MusicDisplayable) : MusicListEvent()
        data class SetNoAdsActive(val noAdsActive: Boolean) : MusicListEvent()
        data class BuyMusicForRewardedAd(val music: MusicDisplayable) : MusicListEvent()
    }
}
