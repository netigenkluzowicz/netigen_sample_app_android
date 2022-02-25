package pl.netigen.drumloops.rock.features.listmusic.presentation

import android.util.Log
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import pl.netigen.drumloops.rock.core.base.BaseViewModel
import pl.netigen.drumloops.rock.features.listmusic.domain.ClickLikeMusicUseCase
import pl.netigen.drumloops.rock.features.listmusic.domain.GetAllMusicUseCase
import pl.netigen.drumloops.rock.features.listmusic.domain.GetLikeMusicUseCase
import pl.netigen.drumloops.rock.features.listmusic.presentation.model.AudioDisplayable
import pl.netigen.drumloops.rock.features.listmusic.presentation.model.MusicListDisplayable
import javax.inject.Inject


@HiltViewModel
class ListMusicViewModel @Inject constructor(
    private val getAllMusicUseCase: GetAllMusicUseCase,
    private val getLikeMusicUseCase: GetLikeMusicUseCase,
    private val clickLikeMusicUseCase: ClickLikeMusicUseCase
) : BaseViewModel<MusicListDisplayable>(initialState = MusicListDisplayable()) {

    init {
        setState { state ->
            state.copy(isLoading = true)
        }
        getAllMusic()
        getLikeMusic()
    }

    private fun getLikeMusic() {
        viewModelScope.launch {
            getLikeMusicUseCase.execute().distinctUntilChanged().collect {
                Log.d("majkel","getLikeMusic")
                setState { state -> state.copy(isLoading = false, likeAudio = it.map { AudioDisplayable(it) }) }
            }
        }
    }

    private fun getAllMusic() {
        getAllMusicUseCase.invoke(Unit, viewModelScope) { result ->
            result.onSuccess {
                setState { state -> state.copy(isLoading = false, allAudio = it.map { AudioDisplayable(it) }) }
            }
            result.onFailure {
                it.message?.let { it1 -> Log.d("majkel" , it1) }
                setState { state -> state.copy(isLoading = false, error = "") }
            }
        }
    }

    fun clickLikeMusic(id: Int) {
        viewModelScope.launch {
            clickLikeMusicUseCase.action(id)
        }
    }

}