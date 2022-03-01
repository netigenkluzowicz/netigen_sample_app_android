package pl.netigen.sampleapp.listmusic.domain.usecase

import pl.netigen.sampleapp.core.base.FlowUseCase
import pl.netigen.sampleapp.listmusic.domain.MusicListRepository
import pl.netigen.sampleapp.listmusic.domain.model.Audio
import javax.inject.Inject

class GetAllMusicFromLocalUseCase @Inject constructor(private val musicListRepository: MusicListRepository) : FlowUseCase<List<Audio>> {
    override suspend fun invoke() = musicListRepository.getMusicFromLocal()
}
