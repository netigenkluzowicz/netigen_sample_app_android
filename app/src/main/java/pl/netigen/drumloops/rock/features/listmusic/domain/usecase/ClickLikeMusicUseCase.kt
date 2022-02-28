package pl.netigen.drumloops.rock.features.listmusic.domain.usecase


import pl.netigen.drumloops.rock.core.base.TypeParamsUseCase
import pl.netigen.drumloops.rock.features.listmusic.domain.MusicListRepository
import javax.inject.Inject


class ClickLikeMusicUseCase @Inject constructor(private val musicListRepository: MusicListRepository) : TypeParamsUseCase<Unit, Int>() {
    override suspend fun action(params: Int) = musicListRepository.setLikeMusic(params)
}