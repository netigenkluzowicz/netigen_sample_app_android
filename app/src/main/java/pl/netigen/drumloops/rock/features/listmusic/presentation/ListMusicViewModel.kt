package pl.netigen.drumloops.rock.features.listmusic.presentation

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import pl.netigen.drumloops.rock.core.base.BaseViewModel
import pl.netigen.drumloops.rock.features.listmusic.domain.GetAllMusicUseCase
import pl.netigen.drumloops.rock.features.listmusic.domain.GetLikeMusicUseCase
import pl.netigen.drumloops.rock.features.listmusic.presentation.model.AudioDisplayable
import pl.netigen.drumloops.rock.features.listmusic.presentation.model.MusicListDisplayable
import javax.inject.Inject


@HiltViewModel
class ListMusicViewModel @Inject constructor(
    private val getAllMusicUseCase: GetAllMusicUseCase,
    private val getLikeMusicUseCase: GetLikeMusicUseCase
) : BaseViewModel<MusicListDisplayable>(initialState = MusicListDisplayable()) {

    init {
        setState { state ->
            state.copy(isLoading = true)
        }
        getAllMusic()
        getLikeMusic()
    }

    private fun getLikeMusic() {
        getLikeMusicUseCase.invoke(Unit, viewModelScope) {

        }
    }

    private fun getAllMusic() {
        getAllMusicUseCase.invoke(Unit, viewModelScope) { result ->
            result.onSuccess {
                setState { state -> state.copy(isLoading = false, allAudio = it.map { AudioDisplayable(it) }) }
            }
            result.onFailure {
                setState { state -> state.copy(isLoading = false, error = "") }
            }
        }
    }

}