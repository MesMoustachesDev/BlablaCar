package dev.blablacar.data.rides.datasource

import androidx.lifecycle.LiveData
import dev.blablacar.data.remote.model.ride.Trip

class RoomRidesDatabase(
    private val ridesDao: RidesDao
) : RideDataSource {

    override fun add(item: Trip) {
        ridesDao.addEvent(item)
    }

    override fun add(items: Iterable<Trip>) {
        ridesDao.addEventList(items.toList())
    }

    override fun update(item: Trip) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun remove(item: Trip) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun remove(specification: dev.blablacar.data.common.Specification) {
        when (specification) {
            is dev.blablacar.data.common.DataSource.Spec.ByRef -> ridesDao.removeEvent(specification.id)
            is dev.blablacar.data.common.DataSource.Spec.All -> ridesDao.clearEventList()
        }
    }

    override fun queryId(specification: String): LiveData<Trip> =
        ridesDao
            .getEventsWithRef(specification)

    override fun queryList(specification: dev.blablacar.data.common.Specification): LiveData<List<Trip>> =
        ridesDao
            .getEvents()
}