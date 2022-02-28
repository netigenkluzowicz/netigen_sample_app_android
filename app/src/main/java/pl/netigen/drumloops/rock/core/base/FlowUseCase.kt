package pl.netigen.drumloops.rock.core.base


import kotlinx.coroutines.flow.Flow


interface FlowUseCase<T> {
    suspend fun invoke(): Flow<T>
}