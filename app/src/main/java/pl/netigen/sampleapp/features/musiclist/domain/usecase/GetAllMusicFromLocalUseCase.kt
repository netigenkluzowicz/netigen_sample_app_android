package pl.netigen.sampleapp.features.musiclist.domain.usecase

import kotlinx.coroutines.flow.Flow
import pl.netigen.sampleapp.core.base.Resource
import pl.netigen.sampleapp.core.base.UseCase
import pl.netigen.sampleapp.features.musiclist.domain.MusicListRepository
import pl.netigen.sampleapp.features.musiclist.domain.model.Music
import javax.inject.Inject

class GetAllMusicFromLocalUseCase @Inject constructor(private val musicListRepository: MusicListRepository) :
    UseCase<Flow<Resource<List<Music>>>, Unit>() {
    override suspend fun action(params: Unit) = musicListRepository.getMusic()
}
