package dev.blablacar.presentation.rides

import android.graphics.Color
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
        is Cell.NeedMore -> R.layout.item_need_more
    }

    inner class RideViewHolder(itemView: View) : GenericViewHolder(itemView) {
        override fun <T> bind(t: T) {
            val item = t as Cell.DataCell
            itemView.name.text = item.driverName
            itemView.price.text = item.price
            itemView.tripStart.text = itemView.resources.getString(R.string.trip_start, item.from)
            itemView.tripStop.text = itemView.resources.getString(R.string.trip_stop, item.to)

            Glide.with(itemView.image)
                .load(item.image)
                .placeholder(R.drawable.logo)
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
            val price: String,
            val priceColor: Int,
            val image: String?
        ) : Cell(id)

        object NeedMore : Cell("-1")
    }
}

fun RideDomain.toCell() = RidesAdapter.Cell.DataCell (
    id = id,
    from = from,
    to = to,
    driverName = driverName,
    image = image,
    priceColor = Color.parseColor(priceColor),
    price = "$price $currency"

)
