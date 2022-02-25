package pl.netigen.drumloops.rock.core.base

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow

@ExperimentalCoroutinesApi
interface FlowUseCase<T> {
    fun execute(): Flow<T>
}