package pl.netigen.sampleapp.features.musiclist.presentation

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
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

    private var jobLike: Job? = null
    private var jobAllMusic: Job? = null
    private var jobSyncMusic: Job? = null

    init {
        setState { state -> state.copy(isLoading = true) }
        getAllMusic()
        getLikeMusic()
        synchronizeMusicFromRemote()
    }

    private fun synchronizeMusicFromRemote() {
        if (jobSyncMusic?.isActive == true) return
        jobSyncMusic = viewModelScope.launch {
            getMusicFromRemoteUseCase.invoke().collect { resource ->
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
        jobLike?.cancel()
        jobLike = viewModelScope.launch {
            getLikeMusicUseCase.invoke().distinctUntilChanged().collect {
                setState { state -> state.copy(isLoading = false, likeMusic = it.map { MusicDisplayable(it) }) }
            }
        }
    }

    private fun getAllMusic() {
        jobAllMusic?.cancel()
        jobAllMusic = viewModelScope.launch {
            getAllMusicFromLocalUseCase.invoke().distinctUntilChanged().collect {
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
