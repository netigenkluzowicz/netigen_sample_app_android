package pl.netigen.sampleapp.core.base

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.*
import timber.log.Timber

inline fun <ResultType, RequestType> networkBoundResource(
    crossinline query: () -> Flow<ResultType>,
    crossinline fetch: suspend () -> RequestType,
    crossinline saveFetchResult: suspend (RequestType) -> Unit,
    emitValue: Boolean = false,
    crossinline shouldFetch: (ResultType?) -> Boolean = { true },
    coroutineDispatcher: CoroutineDispatcher,
) = flow<Resource<ResultType>> {
    Timber.d("()")
    emit(Resource.loading())
    val data = query().firstOrNull()

    if (data != null && emitValue) emit(Resource.success(data))

    if (shouldFetch(data)) {
        Timber.d("(shouldFetch)")
        val fetchResult = fetch()
        saveFetchResult(fetchResult)
    }

    val updatedData = query().first()
    Timber.d("(updatedData)")
    if (emitValue) emit(Resource.success(updatedData))
}.onStart {
    emit(Resource.loading())
}.catch { exception ->
    Timber.d(exception.message)
    emit(Resource.error("An error occurred while fetching data! $exception"))
}.flowOn(coroutineDispatcher)
