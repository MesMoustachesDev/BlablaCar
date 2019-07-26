package dev.blablacar.domain.model

import dev.blablacar.data.rides.model.ride.ArrivalPlace
import dev.blablacar.data.rides.model.ride.DeparturePlace
import dev.blablacar.data.rides.model.ride.Trip
import java.text.SimpleDateFormat
import java.util.*

data class RideDomain (
    val id: String,
    val from: String,
    val to: String,
    val driverName: String,
    val age: Int?,
    val price: Float,
    val currency: String,
    val priceStringValue: String,
    val priceColor: String,
    val image: String?,
    val startDate: Date,
    val arrivalTime: Date,
    val startDistance: Int,
    val arrivalDistance: Int
    )
fun Trip.toDomain(): RideDomain {
    val sdf = SimpleDateFormat("dd/MM/yyyy HH:mm:ss")
    val date = sdf.parse(departureDate)
    val calendar = Calendar.getInstance()
    calendar.time = date
    calendar.add(Calendar.SECOND, duration?.value ?: 0)
    val arrivalDate = calendar.time
    return RideDomain(
        id = permanentId,
        to = arrivalPlace?.toAddress() ?: "",
        from = departurePlace?.toAddress() ?: "",
        driverName = user?.displayName ?: "",
        age = user?.age,
        price = price?.value ?: -1f,
        currency = price?.currency?.toCurrency() ?: "€",
        priceStringValue = priceWithCommission?.stringValue ?: "",
        priceColor = price?.priceColor ?: "",
        image = user?.picture ?: "",
        startDate = date,
        arrivalTime = arrivalDate,
        startDistance = departurePassengerRouting?.routes?.get(0)?.distanceInMeters ?: 0,
        arrivalDistance = arrivalPassengerRouting?.routes?.get(0)?.distanceInMeters ?: 0
    )
}

fun String.toCurrency() = when(this) {
    "EUR" -> "€"
    else -> "£"
}

fun ArrivalPlace.toAddress(): String = "$cityName"

fun DeparturePlace.toAddress(): String = "$cityName"