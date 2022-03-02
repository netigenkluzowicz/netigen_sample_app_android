package pl.netigen.sampleapp.features.musiclist.domain.usecase

import pl.netigen.sampleapp.core.base.UseCase
import pl.netigen.sampleapp.features.musiclist.domain.MusicListRepository
import javax.inject.Inject

class SetBuyMusicUseCase @Inject constructor(private val musicListRepository: MusicListRepository) : UseCase<Unit, Int>() {
    override suspend fun action(params: Int) = musicListRepository.setBuyMusic(params)
}
