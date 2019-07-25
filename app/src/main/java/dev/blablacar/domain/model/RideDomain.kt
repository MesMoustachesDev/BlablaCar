package dev.blablacar.domain.model

import dev.blablacar.data.remote.model.ride.ArrivalPlace
import dev.blablacar.data.remote.model.ride.DeparturePlace
import dev.blablacar.data.remote.model.ride.Trip

data class RideDomain (
    val id: String,
    val from: String,
    val to: String,
    val driverName: String,
    val price: Float,
    val currency: String,
    val priceColor: String,
    val image: String?
)
fun Trip.toDomain(): RideDomain {

/* date formatter in local timezone */
    return RideDomain(
        id = permanentId,
        to = arrivalPlace?.toAddress() ?: "",
        from = departurePlace?.toAddress() ?: "",
        driverName = user?.displayName ?: "",
        price = price?.value ?: -1f,
        currency = price?.currency ?: "€",
        priceColor = price?.priceColor ?: "",
        image = user?.picture ?: ""
    )
}

fun ArrivalPlace.toAddress(): String = "$address"

fun DeparturePlace.toAddress(): String = "$address"