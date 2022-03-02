package pl.netigen.sampleapp.features.musiclist.domain.usecase

import pl.netigen.sampleapp.core.base.UseCase
import pl.netigen.sampleapp.features.musiclist.domain.MusicListRepository
import javax.inject.Inject

class SetBuyMusicUseCase @Inject constructor(private val musicListRepository: MusicListRepository) : UseCase<Unit, Boolean>() {
    override suspend fun action(params: Boolean) = musicListRepository.setBuyMusic(params)
}
