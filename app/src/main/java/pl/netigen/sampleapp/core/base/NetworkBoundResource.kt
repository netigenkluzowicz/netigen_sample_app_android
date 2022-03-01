package pl.netigen.sampleapp.core.base

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.*

inline fun <ResultType, RequestType> networkBoundResource(
    crossinline query: () -> Flow<ResultType>,
    crossinline fetch: suspend () -> RequestType,
    crossinline saveFetchResult: suspend (RequestType) -> Unit,
    emitValue: Boolean = false,
    crossinline shouldFetch: (ResultType?) -> Boolean = { true },
    coroutineDispatcher: CoroutineDispatcher
) = flow<Resource<ResultType>> {
    emit(Resource.loading())
    val data = query().firstOrNull()
    if (data != null && emitValue) {
        emit(Resource.success(data))
    }
    if (shouldFetch(data)) {
        val fetchResult = fetch()
        saveFetchResult(fetchResult)
    }
    val updatedData = query().first()
    if (emitValue)
        emit(Resource.success(updatedData))
}.onStart {
    emit(Resource.loading())
}.catch { exception ->
    emit(Resource.error("An error occurred while fetching data! $exception"))
}.flowOn(coroutineDispatcher)
