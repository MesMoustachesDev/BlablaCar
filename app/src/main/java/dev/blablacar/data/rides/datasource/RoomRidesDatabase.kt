package dev.blablacar.data.rides.datasource

import androidx.lifecycle.LiveData
import dev.blablacar.data.rides.model.ride.Trip

class RoomRidesDatabase(
    private val ridesDao: RidesDao
) : RideDataSource {

    override fun add(item: Trip) {
        ridesDao.addRide(item)
    }

    override fun add(items: Iterable<Trip>) {
        ridesDao.addRideList(items.toList())
    }

    override fun remove(specification: dev.blablacar.data.common.Specification) {
        when (specification) {
            is dev.blablacar.data.common.DataSource.Spec.ByRef -> ridesDao.removeRide(specification.id)
            is dev.blablacar.data.common.DataSource.Spec.All -> ridesDao.clearRideList()
        }
    }

    override fun queryId(specification: String): LiveData<Trip> =
        ridesDao
            .getRidesWithRef(specification)

    override fun queryList(specification: dev.blablacar.data.common.Specification): LiveData<List<Trip>> =
        ridesDao
            .getRides()
}