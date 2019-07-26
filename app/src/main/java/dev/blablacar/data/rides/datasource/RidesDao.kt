package dev.blablacar.data.rides.datasource

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import dev.blablacar.data.rides.model.ride.Trip

@Dao
interface RidesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addRide(ride: Trip)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addRideList(rides: List<Trip>)

    @Query("DELETE FROM rides WHERE permanent_id=:ref")
    fun removeRide(ref: String)

    @Query("DELETE FROM rides")
    fun clearRideList()

    @Query("SELECT * from rides")
    fun getRides(): LiveData<List<Trip>>

    @Query("SELECT * from rides WHERE permanent_id = :ref")
    fun getRidesWithRef(ref: String): LiveData<Trip>
}