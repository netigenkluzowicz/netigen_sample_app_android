package pl.netigen.sampleapp.features.musiclist.domain.usecase

import com.markodevcic.peko.PermissionResult
import kotlinx.coroutines.flow.Flow
import pl.netigen.sampleapp.core.base.UseCase
import pl.netigen.sampleapp.features.musiclist.domain.PermissionManager
import javax.inject.Inject

class AskRecordPermissionUseCase @Inject constructor(private val permissionManager: PermissionManager) : UseCase<Flow<PermissionResult>, Unit>() {
    override suspend fun action(params: Unit): Flow<PermissionResult> = permissionManager.requestRecordPermission()
}
