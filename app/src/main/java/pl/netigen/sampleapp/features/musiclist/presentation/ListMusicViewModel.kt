package pl.netigen.sampleapp.features.musiclist.presentation

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import pl.netigen.sampleapp.core.base.BaseViewModel
import pl.netigen.sampleapp.core.base.Resource
import pl.netigen.sampleapp.features.musiclist.domain.usecase.*
import pl.netigen.sampleapp.features.musiclist.presentation.model.MusicDisplayable
import pl.netigen.sampleapp.features.musiclist.presentation.model.MusicListDisplayable
import javax.inject.Inject

@HiltViewModel
class ListMusicViewModel @Inject constructor(
    private val getAllMusicFromLocalUseCase: GetAllMusicFromLocalUseCase,
    private val getLikeMusicUseCase: GetLikeMusicUseCase,
    private val clickLikeMusicUseCase: ClickLikeMusicUseCase,
    private val getMusicFromRemoteUseCase: GetMusicFromRemoteUseCase,
    private val setBuyAllMusicUseCase: SetBuyAllMusicUseCase,
    private val setBuyMusicUseCase: SetBuyMusicUseCase,
) : BaseViewModel<MusicListDisplayable>(initialState = MusicListDisplayable()) {

    init {
        setState { state -> state.copy(isLoading = true) }
        getAllMusic()
        getLikeMusic()
        synchronizeMusicFromRemote()
    }

    private fun synchronizeMusicFromRemote() {
        viewModelScope.launch {
            getMusicFromRemoteUseCase.action(Unit).collect { resource ->
                when (resource) {
                    is Resource.Error -> setState { state -> state.copy(error = resource.error ?: "") }
                    is Resource.Loading -> setState { state -> state.copy(isLoading = true) }
                    is Resource.Success -> setState { state ->
                        state.copy(allMusic = (resource.data?.map { MusicDisplayable(it) } ?: listOf()))
                    }
                }
            }
        }
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

    fun clickLikeMusic(id: Int) {
        viewModelScope.launch {
            clickLikeMusicUseCase.action(id)
        }
    }

    fun setNoAdsActive(noAdsActive: Boolean) {
        viewModelScope.launch {
            setState { state -> state.copy(isUserPremium = noAdsActive) }
            setBuyAllMusicUseCase.action(noAdsActive)
        }
    }

    fun buyMusic(id: Int) {
        viewModelScope.launch {
            setBuyMusicUseCase.action(id)
        }
    }
}
