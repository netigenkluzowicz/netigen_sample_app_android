package pl.netigen.drumloops.rock.features.listmusic.domain


import kotlinx.coroutines.flow.Flow
import pl.netigen.drumloops.rock.core.base.FlowUseCase
import pl.netigen.drumloops.rock.features.listmusic.domain.model.Audio
import javax.inject.Inject


class GetLikeMusicUseCase @Inject constructor(private val musicListRepository: MusicListRepository) :
    FlowUseCase<List<Audio>> {
    override fun execute(): Flow<List<Audio>> = musicListRepository.getLikeMusic()

}