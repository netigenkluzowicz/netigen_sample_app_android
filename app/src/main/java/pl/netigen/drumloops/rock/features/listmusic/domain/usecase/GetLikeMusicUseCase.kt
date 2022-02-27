package pl.netigen.drumloops.rock.features.listmusic.domain.usecase


import kotlinx.coroutines.ExperimentalCoroutinesApi
import pl.netigen.drumloops.rock.core.base.FlowUseCase
import pl.netigen.drumloops.rock.features.listmusic.domain.MusicListRepository
import pl.netigen.drumloops.rock.features.listmusic.domain.model.Audio
import javax.inject.Inject


@ExperimentalCoroutinesApi
class GetLikeMusicUseCase @Inject constructor(private val musicListRepository: MusicListRepository) : FlowUseCase<List<Audio>> {
    override suspend fun invoke() = musicListRepository.getLikeMusic()
}