package dev.blablacar.data.rides.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import dev.blablacar.data.common.CacheStrategy
import dev.blablacar.data.common.DataSource
import dev.blablacar.data.rides.datasource.ApiService
import dev.blablacar.data.rides.model.ride.Trip
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

    private val rides = localDataSource.queryList(DataSource.Spec.All())
    private val fullLoaded = MutableLiveData<Boolean>()

    init {
        fullLoaded.postValue(true)
    }

    override fun getRides(): LiveData<List<Trip>> = rides
    override fun isFullLoaded(): LiveData<Boolean> = fullLoaded

    override suspend fun fetchRides(
        start: String,
        stop: String,
        forceUpdate: Boolean,
        loadMore: Boolean
    ) {
        if (fetchRunning) return
        val isCacheValid = cacheStrategy.isCacheValid(RideCacheStrategy.Params(start, stop))
        if (loadMore) {
            page++
        }
        if (!isCacheValid || forceUpdate) {
            localDataSource.remove(DataSource.Spec.All())
            page = 1
        }
        if (!isCacheValid || forceUpdate || loadMore) {
            Timber.d("Loading from api")
            fetchRunning = true
            withContext(Dispatchers.IO) {
                try {
                    val result = apiService.getRides(
                        from = start,
                        to = stop,
                        page = page
                    )
                    result.trips?.let {
                        fullLoaded.postValue(result.pager?.page ?: 0 >= result.pager?.pages ?: 0)
                        localDataSource.add(it.filterNotNull())
                        cacheStrategy.newCacheSet(RideCacheStrategy.Params(start, stop))
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