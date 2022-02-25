package pl.netigen.drumloops.rock.features.listmusic.domain


import pl.netigen.drumloops.rock.core.base.UseCase
import javax.inject.Inject


class ClickLikeMusicUseCase @Inject constructor(private val musicListRepository: MusicListRepository) :
    UseCase<Unit, Int>() {
    override suspend fun action(id: Int) {
        musicListRepository.setLikeMusic(id)
    }


}