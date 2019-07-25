package dev.blablacar.domain.usecase

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.Transformations
import dev.blablacar.data.rides.repository.TripRepository
import dev.blablacar.domain.model.ResultRidesDomain
import dev.blablacar.domain.model.toDomain
import dev.mesmoustaches.coroutines.CoroutineUseCase
import timber.log.Timber

class GetRidesUseCase(
    private val rideRepository: TripRepository
) : CoroutineUseCase<GetRidesUseCase.Params, ResultRidesDomain>() {

    val data = MediatorLiveData<ResultRidesDomain>()

    private val rides = Transformations.map(rideRepository.getRides()) { list ->
        list.map { it.toDomain() }
    }
    private val fullLoaded = rideRepository.isFullLoaded()

    init {
        data.addSource(rides) {
            data.postValue(ResultRidesDomain(fullLoaded.value ?: true, it))
        }
        data.addSource(fullLoaded) {
            data.postValue(ResultRidesDomain(it, rides.value))
        }
    }

    override suspend fun createCoroutine(input: Params?) {
        try {
            rideRepository
                .fetchRides(forceUpdate = input?.forceUpdate ?: false,
                    loadMore = input?.loadMore?: false,
                    start = input?.startCity ?: "",
                    stop = input?.stopCity ?: "")
        } catch (e: Exception) {
            Timber.e(e, "Error getting rides")
            throw e
        }
    }

    data class Params(
        val startCity: String,
        val stopCity: String,
        val forceUpdate: Boolean = false,
        val loadMore: Boolean = false
    )
}