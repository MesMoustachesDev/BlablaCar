package dev.blablacar.data.rides.model.ride


import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import com.google.gson.reflect.TypeToken
import java.util.*

@Entity(tableName = "rides")
data class Trip(
    @SerializedName("answer_delay")
    @ColumnInfo(name ="answer_delay")
    val answerDelay: Int?,
    @SerializedName("arrival_meeting_point")
    @ColumnInfo(name ="arrival_meeting_point")
    val arrivalMeetingPoint: ArrivalMeetingPoint?,
    @SerializedName("arrival_passenger_routing")
    @ColumnInfo(name ="arrival_passenger_routing")
    val arrivalPassengerRouting: ArrivalPassengerRouting?,
    @SerializedName("arrival_place")
    @ColumnInfo(name ="arrival_place")
    val arrivalPlace: ArrivalPlace?,
    @SerializedName("booking_mode")
    @ColumnInfo(name ="booking_mode")
    val bookingMode: String?,
    @SerializedName("booking_type")
    @ColumnInfo(name ="booking_type")
    val bookingType: String?,
    @SerializedName("car")
    @ColumnInfo(name ="car")
    val car: Car?,
    @SerializedName("commission")
    @ColumnInfo(name ="commission")
    val commission: Commission?,
    @SerializedName("corridoring_type")
    @ColumnInfo(name ="corridoring_type")
    val corridoringType: String?,
    @SerializedName("departure_date")
    @ColumnInfo(name ="departure_date")
    val departureDate: String?,
    @SerializedName("departure_passenger_routing")
    @ColumnInfo(name ="departure_passenger_routing")
    val departurePassengerRouting: DeparturePassengerRouting?,
    @SerializedName("departure_place")
    @ColumnInfo(name ="departure_place")
    val departurePlace: DeparturePlace?,
    @SerializedName("distance")
    @ColumnInfo(name ="distance")
    val distance: Distance?,
    @SerializedName("duration")
    @ColumnInfo(name ="duration")
    val duration: Duration?,
    @SerializedName("freeway")
    @ColumnInfo(name ="freeway")
    val freeway: Boolean?,
    @SerializedName("is_comfort")
    @ColumnInfo(name ="is_comfort")
    val isComfort: Boolean?,
    @SerializedName("links")
    @ColumnInfo(name ="links")
    val links: LinksX?,
    @SerializedName("locations_to_display")
    @ColumnInfo(name ="locations_to_display")
    val locationsToDisplay: List<String?>?,
    @SerializedName("multimodal_id")
    @ColumnInfo(name ="multimodal_id")
    val multimodalId: MultimodalId?,
    @SerializedName("permanent_id")
    @ColumnInfo(name ="permanent_id")
    @PrimaryKey
    val permanentId: String,
    @SerializedName("price")
    @ColumnInfo(name ="price")
    val price: Price?,
    @SerializedName("price_with_commission")
    @ColumnInfo(name ="price_with_commission")
    val priceWithCommission: PriceWithCommission?,
    @SerializedName("price_without_commission")
    @ColumnInfo(name ="price_without_commission")
    val priceWithoutCommission: PriceWithoutCommission?,
    @SerializedName("seats")
    @ColumnInfo(name ="seats")
    val seats: Int?,
    @SerializedName("seats_left")
    @ColumnInfo(name ="seats_left")
    val seatsLeft: Int?,
    @SerializedName("user")
    @ColumnInfo(name ="user")
    val user: UserX?,
    @SerializedName("vehicle_pictures")
    @ColumnInfo(name ="vehicle_pictures")
    val vehiclePictures: List<String?>?,
    @SerializedName("viaggio_rosa")
    @ColumnInfo(name ="viaggio_rosa")
    val viaggioRosa: Boolean?
)

class ArrivalMeetingPointConverter {
    @TypeConverter
    fun stringToArrivalMeetingPointModel(data: String?): ArrivalMeetingPoint? {
        val gson = Gson()
        if (data == null) {
            return null
        }
        return gson.fromJson(data, ArrivalMeetingPoint::class.java)
    }

    @TypeConverter
    fun arrivalMeetingPointModelToString(arrivalMeetingPoint: ArrivalMeetingPoint?): String {
        val gson = Gson()
        return gson.toJson(arrivalMeetingPoint)
    }
}

class ArrivalPassengerRoutingConverter {
    @TypeConverter
    fun stringToArrivalPassengerRoutingModel(data: String?): ArrivalPassengerRouting? {
        val gson = Gson()
        if (data == null) {
            return null
        }
        return gson.fromJson(data, ArrivalPassengerRouting::class.java)
    }

    @TypeConverter
    fun arrivalPassengerRoutingModelToString(arrivalPassengerRouting: ArrivalPassengerRouting?): String {
        val gson = Gson()
        return gson.toJson(arrivalPassengerRouting)
    }
}

class ArrivalPlaceConverter {
    @TypeConverter
    fun stringToArrivalPlaceModel(data: String?): ArrivalPlace? {
        val gson = Gson()
        if (data == null) {
            return null
        }
        return gson.fromJson(data, ArrivalPlace::class.java)
    }

    @TypeConverter
    fun arrivalPlaceModelToString(arrivalPlace: ArrivalPlace?): String {
        val gson = Gson()
        return gson.toJson(arrivalPlace)
    }
}

class CarConverter {
    @TypeConverter
    fun stringToCarModel(data: String?): Car? {
        val gson = Gson()
        if (data == null) {
            return null
        }
        return gson.fromJson(data, Car::class.java)
    }

    @TypeConverter
    fun carModelToString(car: Car?): String {
        val gson = Gson()
        return gson.toJson(car)
    }
}

class CommissionConverter {
    @TypeConverter
    fun stringToCommissionModel(data: String?): Commission? {
        val gson = Gson()
        if (data == null) {
            return null
        }
        return gson.fromJson(data, Commission::class.java)
    }

    @TypeConverter
    fun commissionModelToString(commission: Commission?): String {
        val gson = Gson()
        return gson.toJson(commission)
    }
}

class DeparturePassengerRoutingConverter {
    @TypeConverter
    fun stringToDeparturePassengerRoutingModel(data: String?): DeparturePassengerRouting? {
        val gson = Gson()
        if (data == null) {
            return null
        }
        return gson.fromJson(data, DeparturePassengerRouting::class.java)
    }

    @TypeConverter
    fun departurePassengerRoutingModelToString(departurePassengerRouting: DeparturePassengerRouting?): String {
        val gson = Gson()
        return gson.toJson(departurePassengerRouting)
    }
}

class DeparturePlaceConverter {
    @TypeConverter
    fun stringToDeparturePlaceModel(data: String?): DeparturePlace? {
        val gson = Gson()
        if (data == null) {
            return null
        }
        return gson.fromJson(data, DeparturePlace::class.java)
    }

    @TypeConverter
    fun departurePlaceModelToString(departurePlace: DeparturePlace?): String {
        val gson = Gson()
        return gson.toJson(departurePlace)
    }
}

class DistanceConverter {
    @TypeConverter
    fun stringToDistanceModel(data: String?): Distance? {
        val gson = Gson()
        if (data == null) {
            return null
        }
        return gson.fromJson(data, Distance::class.java)
    }

    @TypeConverter
    fun distanceModelToString(distance: Distance?): String {
        val gson = Gson()
        return gson.toJson(distance)
    }
}

class DurationConverter {
    @TypeConverter
    fun stringToDurationModel(data: String?): Duration? {
        val gson = Gson()
        if (data == null) {
            return null
        }
        return gson.fromJson(data, Duration::class.java)
    }

    @TypeConverter
    fun durationModelToString(duration: Duration?): String {
        val gson = Gson()
        return gson.toJson(duration)
    }
}

class LinksXConverter {
    @TypeConverter
    fun stringToLinksXModel(data: String?): LinksX? {
        val gson = Gson()
        if (data == null) {
            return null
        }
        return gson.fromJson(data, LinksX::class.java)
    }

    @TypeConverter
    fun linksXModelToString(linksX: LinksX?): String {
        val gson = Gson()
        return gson.toJson(linksX)
    }
}

class MultimodalIdConverter {
    @TypeConverter
    fun stringToMultimodalIdModel(data: String?): MultimodalId? {
        val gson = Gson()
        if (data == null) {
            return null
        }
        return gson.fromJson(data, MultimodalId::class.java)
    }

    @TypeConverter
    fun multimodalIdModelToString(multimodalId: MultimodalId?): String {
        val gson = Gson()
        return gson.toJson(multimodalId)
    }
}

class PriceConverter {
    @TypeConverter
    fun stringToPriceModel(data: String?): Price? {
        val gson = Gson()
        if (data == null) {
            return null
        }
        return gson.fromJson(data, Price::class.java)
    }

    @TypeConverter
    fun priceModelToString(price: Price?): String {
        val gson = Gson()
        return gson.toJson(price)
    }
}

class PriceWithCommissionConverter {
    @TypeConverter
    fun stringToPriceWithCommissionModel(data: String?): PriceWithCommission? {
        val gson = Gson()
        if (data == null) {
            return null
        }
        return gson.fromJson(data, PriceWithCommission::class.java)
    }

    @TypeConverter
    fun priceWithCommissionModelToString(priceWithCommission: PriceWithCommission?): String {
        val gson = Gson()
        return gson.toJson(priceWithCommission)
    }
}

class PriceWithoutCommissionConverter {
    @TypeConverter
    fun stringToPriceWithoutCommissionModel(data: String?): PriceWithoutCommission? {
        val gson = Gson()
        if (data == null) {
            return null
        }
        return gson.fromJson(data, PriceWithoutCommission::class.java)
    }

    @TypeConverter
    fun priceWithoutCommissionModelToString(priceWithoutCommission: PriceWithoutCommission?): String {
        val gson = Gson()
        return gson.toJson(priceWithoutCommission)
    }
}

class UserXConverter {
    @TypeConverter
    fun stringToUserXModel(data: String?): UserX? {
        val gson = Gson()
        if (data == null) {
            return null
        }
        return gson.fromJson(data, UserX::class.java)
    }

    @TypeConverter
    fun userXModelToString(userX: UserX?): String {
        val gson = Gson()
        return gson.toJson(userX)
    }
}

class StringListConverter {
    @TypeConverter
    fun stringToStringList(data: String?): List<String>? {
        val gson = Gson()
        if (data == null) {
            return Collections.emptyList()
        }

        val listType = object : TypeToken<List<String>>() {

        }.type
        return gson.fromJson(data, listType)
    }

    @TypeConverter
    fun stringListToString(someObjects: List<String>): String? {
        val gson = Gson()
        return gson.toJson(someObjects)
    }
}