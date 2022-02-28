package pl.netigen.sampleapp.listmusic.presentation

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import pl.netigen.sampleapp.core.base.BaseViewModel
import pl.netigen.sampleapp.core.base.Resource
import pl.netigen.sampleapp.listmusic.domain.usecase.ClickLikeMusicUseCase
import pl.netigen.sampleapp.listmusic.domain.usecase.GetAllMusicFromLocalUseCase
import pl.netigen.sampleapp.listmusic.domain.usecase.GetLikeMusicUseCase
import pl.netigen.sampleapp.listmusic.domain.usecase.GetMusicFromRemoteUseCase
import pl.netigen.sampleapp.listmusic.presentation.model.AudioDisplayable
import pl.netigen.sampleapp.listmusic.presentation.model.MusicListDisplayable
import javax.inject.Inject



@HiltViewModel
class ListMusicViewModel @Inject constructor(
    private val getAllMusicFromLocalUseCase: GetAllMusicFromLocalUseCase,
    private val getLikeMusicUseCase: GetLikeMusicUseCase,
    private val clickLikeMusicUseCase: ClickLikeMusicUseCase,
    private val getMusicFromRemoteUseCase: GetMusicFromRemoteUseCase
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
                    is Resource.Error -> setState { state -> state.copy(error = resource.error) }
                    is Resource.Loading -> setState { state -> state.copy(isLoading = true) }
                    is Resource.Success -> setState { state ->
                        state.copy(allAudio = (resource.data?.map { AudioDisplayable(it) } ?: listOf()))
                    }
                }

            }
        }
    }

    private fun getLikeMusic() {
        jobLike?.cancel()
        jobLike = viewModelScope.launch {
            getLikeMusicUseCase.invoke().distinctUntilChanged().collect {
                setState { state -> state.copy(isLoading = false, likeAudio = it.map { AudioDisplayable(it) }) }
            }
        }
    }

    private fun getAllMusic() {
        jobAllMusic?.cancel()
        jobAllMusic = viewModelScope.launch {
            getAllMusicFromLocalUseCase.invoke().distinctUntilChanged().collect {
                setState { state -> state.copy(isLoading = false, allAudio = it.map { AudioDisplayable(it) }) }
            }
        }
    }

    fun clickLikeMusic(id: Int) {
        viewModelScope.launch {
            clickLikeMusicUseCase.action(id)
        }
    }

}