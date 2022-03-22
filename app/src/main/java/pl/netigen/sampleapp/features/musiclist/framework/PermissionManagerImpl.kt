package pl.netigen.sampleapp.features.musiclist.framework

import android.Manifest
import android.content.Context
import com.markodevcic.peko.Peko
import com.markodevcic.peko.PermissionResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import pl.netigen.sampleapp.features.musiclist.domain.PermissionManager
import javax.inject.Inject

class PermissionManagerImpl @Inject constructor(private val context: Context) : PermissionManager {
    override suspend fun requestRecordPermission(): Flow<PermissionResult> =
        flowOf(Peko.requestPermissionsAsync(context, Manifest.permission.RECORD_AUDIO))
}
