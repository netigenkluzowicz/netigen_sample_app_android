package pl.netigen.sampleapp.features.musiclist.presentation

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import pl.netigen.sampleapp.core.base.BaseViewModel
import pl.netigen.sampleapp.core.base.Resource
import pl.netigen.sampleapp.features.musiclist.domain.usecase.*
import pl.netigen.sampleapp.features.musiclist.presentation.model.MusicDisplayable
import pl.netigen.sampleapp.features.musiclist.presentation.model.MusicListContract
import javax.inject.Inject

@HiltViewModel
class ListMusicViewModel @Inject constructor(
    private val getAllMusicFromLocalUseCase: GetAllMusicFromLocalUseCase,
    private val getLikeMusicUseCase: GetLikeMusicUseCase,
    private val clickLikeMusicUseCase: ClickLikeMusicUseCase,
    private val setBuyAllMusicUseCase: SetBuyAllMusicUseCase,
    private val setBuyMusicUseCase: SetBuyMusicUseCase,
) : BaseViewModel<MusicListContract.MusicListState, MusicListContract.MusicListEvent>() {

    init {
        setState { state -> state.copy(isLoading = true) }
        getAllMusic()
        getLikeMusic()
        subscribeToEvents()
    }


    private fun getLikeMusic() {
        viewModelScope.launch {
            getLikeMusicUseCase.action(Unit).distinctUntilChanged().collect {
                setState { state -> state.copy(isLoading = false, likeMusic = it.map { MusicDisplayable(it) }) }
            }
        }
    }

    private fun getAllMusic() {
        viewModelScope.launch {
            getAllMusicFromLocalUseCase.action(Unit).distinctUntilChanged().collect {
                setState { state -> state.copy(isLoading = false, allMusic = it.map { MusicDisplayable(it) }) }
            }
        }
    }

    override fun handleEvents(event: MusicListContract.MusicListEvent) {
        when (event) {
            is MusicListContract.MusicListEvent.LikeMusicClicked -> {
                viewModelScope.launch {
                    clickLikeMusicUseCase.action(event.music.id)
                }
            }
            is MusicListContract.MusicListEvent.SetNoAdsActive -> {
                viewModelScope.launch {
                    setBuyAllMusicUseCase.action(event.noAdsActive)
                }
            }
            is MusicListContract.MusicListEvent.BuyMusicForRewardedAd -> {
                viewModelScope.launch {
                    setBuyMusicUseCase.action(event.music.id)
                }
            }
        }
    }

    override fun setInitialState(): MusicListContract.MusicListState = MusicListContract.MusicListState()
}
