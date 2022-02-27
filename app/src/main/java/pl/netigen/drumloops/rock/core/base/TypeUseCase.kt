package pl.netigen.drumloops.rock.core.base

import kotlinx.coroutines.*

abstract class TypeUseCase<out Type> {

    abstract suspend fun action(): Type

    operator fun invoke(
        scope: CoroutineScope,
        executionDispatcher: CoroutineDispatcher = Dispatchers.IO,
        onResult: (Result<Type>) -> Unit = {}
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