package dev.blablacar.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import dev.blablacar.data.rides.model.ride.*
import dev.blablacar.data.rides.datasource.RidesDao

@Database(entities = [Trip::class], version = 2, exportSchema = false)

@TypeConverters(
    UserXConverter::class,
    PriceWithoutCommissionConverter::class,
    PriceWithCommissionConverter::class,
    PriceConverter::class,
    MultimodalIdConverter::class,
    LinksXConverter::class,
    DurationConverter::class,
    DistanceConverter::class,
    DeparturePlaceConverter::class,
    DeparturePassengerRoutingConverter::class,
    CommissionConverter::class,
    CarConverter::class,
    ArrivalPlaceConverter::class,
    ArrivalPassengerRoutingConverter::class,
    ArrivalMeetingPointConverter::class,
    StringListConverter::class)

abstract class DataBase : RoomDatabase() {
    abstract fun ridesDao(): RidesDao
}