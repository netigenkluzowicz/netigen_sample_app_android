package pl.netigen.sampleapp.features.musiclist.domain.usecase

import pl.netigen.sampleapp.core.base.FlowUseCase
import pl.netigen.sampleapp.features.musiclist.domain.MusicListRepository
import pl.netigen.sampleapp.features.musiclist.domain.model.Music
import javax.inject.Inject

class GetAllMusicFromLocalUseCase @Inject constructor(private val musicListRepository: MusicListRepository) : FlowUseCase<List<Music>> {
    override suspend fun invoke() = musicListRepository.getMusicFromLocal()
}
