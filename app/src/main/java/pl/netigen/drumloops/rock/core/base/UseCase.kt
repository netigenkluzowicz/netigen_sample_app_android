package pl.netigen.drumloops.rock.core.base

import kotlinx.coroutines.*

abstract class UseCase {

    abstract suspend fun action()

    operator fun invoke(
        scope: CoroutineScope,
        executionDispatcher: CoroutineDispatcher = Dispatchers.IO,
        onResult: (Result<Unit>) -> Unit = {}
    ) {
        scope.launch {
            val result = withContext(executionDispatcher) {
                kotlin.runCatching {
                    action()
                }
            }
            onResult(result)
        }
    }
}