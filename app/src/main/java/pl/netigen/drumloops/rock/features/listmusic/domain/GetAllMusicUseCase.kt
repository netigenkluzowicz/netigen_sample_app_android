package pl.netigen.drumloops.rock.features.listmusic.domain


import pl.netigen.drumloops.rock.core.base.UseCase
import pl.netigen.drumloops.rock.features.listmusic.domain.model.Audio

class GetAllMusicUseCase(private val musicListRepository: MusicListRepository) :
    UseCase<List<Audio>, Unit>() {
    override suspend fun action(params: Unit) = musicListRepository.getMusic()


}