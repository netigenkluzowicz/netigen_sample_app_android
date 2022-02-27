package pl.netigen.drumloops.rock.core.base

import kotlinx.coroutines.*

abstract class ParamsUseCase<in Params> {

    abstract suspend fun action(params: Params)

    operator fun invoke(
        params: Params,
        scope: CoroutineScope,
        executionDispatcher: CoroutineDispatcher = Dispatchers.IO,
        onResult: (Result<Unit>) -> Unit = {}
    ) {
        scope.launch {
            val result = withContext(executionDispatcher) {
                kotlin.runCatching {
                    action(params)
                }
            }
            onResult(result)
        }
    }
}