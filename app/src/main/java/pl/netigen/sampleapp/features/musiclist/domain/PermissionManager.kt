package pl.netigen.sampleapp.features.musiclist.domain

import com.markodevcic.peko.PermissionResult
import kotlinx.coroutines.flow.Flow

interface PermissionManager {
    suspend fun requestRecordPermission(): Flow<PermissionResult>
}
