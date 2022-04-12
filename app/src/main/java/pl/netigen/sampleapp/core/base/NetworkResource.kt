package pl.netigen.sampleapp.core.base

import kotlinx.coroutines.flow.flow
import retrofit2.Response

inline fun <REMOTE> networkResource(
    crossinline fetchFromRemote: suspend () -> Response<REMOTE>,
) = flow<Resource<REMOTE>> {
    emit(Resource.loading())
    val fetchResult = fetchFromRemote()
    if (fetchResult.isSuccessful) {
        val body = fetchResult.body()
        if (body != null) emit(Resource.success(body)) else emit(Resource.error("Body is null"))
    } else {
        val msg = fetchResult.errorBody()?.string()
        val errorMsg = if (msg.isNullOrEmpty()) {
            fetchResult.message()
        } else {
            msg
        }
        emit(Resource.error(errorMsg))
    }
}
