package dev.blablacar.presentation.rides

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import dev.blablacar.R
import dev.blablacar.domain.model.RideDomain
import dev.mesmoustaches.android.view.GenericViewHolder
import kotlinx.android.synthetic.main.item_ride.view.*
import java.text.DecimalFormat
import java.text.SimpleDateFormat


class RidesAdapter(private val needMore: (Int) -> Unit) : RecyclerView.Adapter<GenericViewHolder>() {

    private var items = listOf<Cell>()

    override fun onBindViewHolder(holder: GenericViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenericViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            viewType,
            parent,
            false
        )
        return when (viewType) {
            R.layout.item_ride -> RideViewHolder(view)
            else -> NeedMoreViewHolder(view, needMore)
        }
    }

    override fun getItemCount(): Int = items.size

    override fun getItemViewType(position: Int): Int = when (items[position]) {
        is Cell.DataCell -> R.layout.item_ride
        is Cell.NeedMore -> R.layout.item_ride_loading
    }

    inner class RideViewHolder(itemView: View) : GenericViewHolder(itemView) {
        override fun <T> bind(t: T) {
            val item = t as Cell.DataCell
            itemView.name.text = item.age?.let {
                itemView.resources.getString(R.string.driver_name_and_age, item.driverName, it)
            } ?: item.driverName
            itemView.price.text = item.price
            itemView.tripStart.text = itemView.resources.getString(R.string.trip_start, item.startTime, item.from, item.startDistance)
            itemView.tripStop.text = itemView.resources.getString(R.string.trip_stop, item.arrivalTime, item.to, item.arrivalDistance)
            itemView.date.text = item.startDate
            itemView.time.text = item.startTime

            Glide.with(itemView.image)
                .load(item.image)
                .placeholder(R.drawable.ic_placeholder)
                .into(itemView.image)
        }
    }

    inner class NeedMoreViewHolder(itemView: View, private val needMore: (Int) -> Unit) : GenericViewHolder(itemView) {
        override fun <T> bind(t: T) {
            needMore.invoke(itemCount)
        }
    }

    fun update(list: List<Cell>) {
        val diffResult = DiffUtil.calculateDiff(DiffCallback(items, list))
        items = list
        diffResult.dispatchUpdatesTo(this)
    }

    class DiffCallback(
        private val oldList: List<Cell>,
        private val newList: List<Cell>
    ) : DiffUtil.Callback() {

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            val old = oldList[oldItemPosition]
            val new = newList[newItemPosition]
            return (old.id == new.id)
        }

        override fun getOldListSize(): Int = oldList.size

        override fun getNewListSize(): Int = newList.size

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            val old = oldList[oldItemPosition]
            val new = newList[newItemPosition]
            return (old is Cell.DataCell && new is Cell.DataCell && old.driverName == new.driverName)
        }
    }

    sealed class Cell(val id: String) {
        class DataCell(
            id: String,
            val from: String,
            val to: String,
            val driverName: String,
            val age: Int?,
            val price: String,
            val image: String?,
            val startDate: String,
            val startTime: String,
            val arrivalTime: String,
            val startDistance: String,
            val arrivalDistance: String
        ) : Cell(id)

        object NeedMore : Cell("-1")
    }
}

fun RideDomain.toCell(): RidesAdapter.Cell.DataCell {
    val dateFormat = SimpleDateFormat("dd MMMM")
    val timeFormat = SimpleDateFormat("HH'h'mm")
    val decimalFormat = DecimalFormat("##.##")
    return RidesAdapter.Cell.DataCell(
        id = id,
        from = from,
        to = to,
        driverName = driverName,
        age = age,
        image = image,
        price = priceStringValue,
        startDate = dateFormat.format(startDate),
        startTime = timeFormat.format(startDate),
        arrivalTime = timeFormat.format(arrivalTime),
        startDistance = decimalFormat.format(startDistance / 1000f),
        arrivalDistance = decimalFormat.format(arrivalDistance / 1000f)
    )
}
