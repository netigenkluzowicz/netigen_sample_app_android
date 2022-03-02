package pl.netigen.sampleapp.features.musiclist.domain.usecase

import pl.netigen.sampleapp.core.base.UseCase
import pl.netigen.sampleapp.features.musiclist.domain.MusicListRepository
import javax.inject.Inject

class SetBuyAllMusicUseCase @Inject constructor(private val musicListRepository: MusicListRepository) : UseCase<Unit, Boolean>() {
    override suspend fun action(params: Boolean) = musicListRepository.setBuyAllMusic(params)
}
