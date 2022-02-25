package pl.netigen.drumloops.rock.features.listmusic.domain


import pl.netigen.drumloops.rock.core.base.UseCase
import pl.netigen.drumloops.rock.features.listmusic.domain.model.Audio
import javax.inject.Inject
import javax.inject.Singleton


class ClickLikeMusicUseCase @Inject constructor(private val musicListRepository: MusicListRepository) :
    UseCase<Unit, Int>() {
    override suspend fun action(id: Int) {
       musicListRepository.setLikeMusic(id)
    }


}