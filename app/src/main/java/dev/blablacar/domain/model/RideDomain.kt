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
    val price: Float,
    val currency: String,
    val priceStringValue: String,
    val priceColor: String,
    val image: String?,
    val date: Date
)
fun Trip.toDomain(): RideDomain {
    val sdf = SimpleDateFormat("dd/MM/yyyy HH:mm:ss")
    return RideDomain(
        id = permanentId,
        to = arrivalPlace?.toAddress() ?: "",
        from = departurePlace?.toAddress() ?: "",
        driverName = user?.displayName ?: "",
        price = price?.value ?: -1f,
        currency = price?.currency?.toCurrency() ?: "€",
        priceStringValue = priceWithCommission?.stringValue ?: "",
        priceColor = price?.priceColor ?: "",
        image = user?.picture ?: "",
        date = sdf.parse(departureDate)
    )
}

fun String.toCurrency() = when(this) {
    "EUR" -> "€"
    else -> "£"
}

fun ArrivalPlace.toAddress(): String = "$cityName"

fun DeparturePlace.toAddress(): String = "$cityName"