package pl.netigen.sampleapp.core.base


import kotlinx.coroutines.flow.Flow


interface FlowUseCase<T> {
    suspend fun invoke(): Flow<T>
}