package pl.netigen.drumloops.rock.features.listmusic.domain.usecase



import pl.netigen.drumloops.rock.core.base.FlowUseCase
import pl.netigen.drumloops.rock.core.base.Resource
import pl.netigen.drumloops.rock.features.listmusic.domain.MusicListRepository
import pl.netigen.drumloops.rock.features.listmusic.domain.model.Audio
import javax.inject.Inject


class GetMusicFromRemoteUseCase @Inject constructor(private val musicListRepository: MusicListRepository) : FlowUseCase<Resource<List<Audio>>> {
    override suspend fun invoke() = musicListRepository.getMusicFromRemote()
}