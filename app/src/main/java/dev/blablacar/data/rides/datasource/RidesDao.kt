package dev.blablacar.data.rides.datasource

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import dev.blablacar.data.remote.model.ride.Trip

@Dao
interface RidesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addEvent(ride: Trip)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addEventList(rides: List<Trip>)

    @Query("DELETE FROM rides WHERE permanent_id=:ref")
    fun removeEvent(ref: String)

    @Query("DELETE FROM rides")
    fun clearEventList()

    @Query("SELECT * from rides")
    fun getEvents(): LiveData<List<Trip>>

    @Query("SELECT * from rides WHERE permanent_id = :ref")
    fun getEventsWithRef(ref: String): LiveData<Trip>
}