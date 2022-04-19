package pl.netigen.sampleapp.features.musiclist.presentation

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import pl.netigen.extensions.launchIO
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
        getAllMusic()
        getLikeMusic()
    }

    private fun getLikeMusic() {
        getLikeMusicUseCase(Unit, viewModelScope) { result ->
            result.onSuccess {
                launchIO {
                    it.distinctUntilChanged().collect {
                        setState { state -> state.copy(likeMusic = it.map { MusicDisplayable(it) }) }
                    }
                }
            }
            result.onFailure {
                setState { state -> state.copy(error = it.message ?: "") }
            }
        }
    }

    private fun getAllMusic() {
        getAllMusicFromLocalUseCase(Unit, viewModelScope) { result ->
            result.onSuccess { flow ->
                launchIO {
                    flow.distinctUntilChanged().collect {
                        when (it) {
                            is Resource.Success -> {
                                it.data?.let {
                                    setState { state -> state.copy(isLoading = false, allMusic = it.map { MusicDisplayable(it) }) }
                                }
                            }
                            is Resource.Loading -> {
                                setState { state -> state.copy(isLoading = true) }
                            }
                            is Resource.Error -> {
                                setState { state -> state.copy(error = it.error ?: "") }
                            }
                        }
                    }
                }
            }
            result.onFailure {
                setState { state -> state.copy(error = it.message ?: "") }
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
