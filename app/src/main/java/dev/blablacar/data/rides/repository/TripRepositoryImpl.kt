package dev.blablacar.data.rides.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import dev.blablacar.data.common.CacheStrategy
import dev.blablacar.data.common.DataSource
import dev.blablacar.data.remote.ApiService
import dev.blablacar.data.remote.model.ride.Trip
import dev.blablacar.data.rides.datasource.RideDataSource
import dev.blablacar.data.rides.repository.cache.RideCacheStrategy
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import timber.log.Timber

class TripRepositoryImpl(
    private val apiService: ApiService,
    private val localDataSource: RideDataSource,
    private val cacheStrategy: CacheStrategy<RideCacheStrategy.Params>
) : TripRepository {

    private var page: Int = 1
    @Volatile
    private var fetchRunning = false

    private val events = localDataSource.queryList(DataSource.Spec.All())
    private val fullLoaded = MutableLiveData<Boolean>()

    init {
        fullLoaded.postValue(true)
    }

    override fun getRides(): LiveData<List<Trip>> = events
    override fun isFullLoaded(): LiveData<Boolean> = fullLoaded

    override suspend fun fetchEvents(
        forceUpdate: Boolean,
        loadMore: Boolean
    ) {
        if (fetchRunning) return
        if (forceUpdate) {
            fullLoaded.postValue(false)
            page = 1
        }
        if (loadMore) {
            page++
        }
        if (!cacheStrategy.isCacheValid(RideCacheStrategy.Params("Paris", "Rennes"))
            || forceUpdate
            || loadMore
        ) {
            Timber.d("Loading from api")
            fetchRunning = true
            withContext(Dispatchers.IO) {
                try {
                    val result = apiService.getEvents(
                        from = "Paris",
                        to = "Rennes",
                        page = page
                    )
                    if (!loadMore) {
                        localDataSource.remove(DataSource.Spec.All())
                    }
                    result.trips?.let {
                        fullLoaded.postValue(result.fullTripsCount ?: 0 < events.value?.size ?: 0)
                        localDataSource.add(it.filterNotNull())
                        cacheStrategy.newCacheSet(RideCacheStrategy.Params("Paris", "Rennes"))
                    }
                } catch (error: Throwable) {
                    throw error
                } finally {
                    fetchRunning = false
                }
            }
        } else {
            Timber.d("Loading from cache")
        }
    }
}