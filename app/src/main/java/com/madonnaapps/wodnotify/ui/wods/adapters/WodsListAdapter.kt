package com.madonnaapps.wodnotify.ui.wods.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.madonnaapps.wodnotify.R
import com.madonnaapps.wodnotify.data.local.entities.WodEntity
import kotlinx.android.synthetic.main.list_item_wods.view.*
import java.text.SimpleDateFormat
import java.util.*

class WodsListAdapter(
    private val interaction: Interaction? = null
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val diffCallback = object : DiffUtil.ItemCallback<WodEntity>() {

        override fun areItemsTheSame(oldItem: WodEntity, newItem: WodEntity): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: WodEntity, newItem: WodEntity): Boolean {
            return oldItem.title == newItem.title &&
                    oldItem.author == newItem.author &&
                    oldItem.date == newItem.date
        }

    }

    private val differ = AsyncListDiffer(this, diffCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.list_item_wods,
                parent,
                false
            ),
            interaction
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is ViewHolder -> {
                holder.bind(differ.currentList[position])
            }
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    fun submitList(list: List<WodEntity>) {
        differ.submitList(list)
    }

    companion object {
        @JvmStatic
        private val MONTH_DATE_FORMAT = SimpleDateFormat("MMM", Locale.US)

        @JvmStatic
        private val DAY_DATE_FORMAT = SimpleDateFormat("d", Locale.US)
    }

    class ViewHolder constructor(
        itemView: View,
        private val interaction: Interaction?
    ) : RecyclerView.ViewHolder(itemView) {

        fun bind(item: WodEntity) = with(itemView) {
            itemView.setOnClickListener {
                interaction?.onItemSelected(adapterPosition, item)
            }

            itemView.tv_wod_item_title.text = item.title
            itemView.tv_wod_item_author.text = item.author
            itemView.tv_wod_item_month.text = MONTH_DATE_FORMAT.format(item.date)
            itemView.tv_wod_item_day.text = DAY_DATE_FORMAT.format(item.date)
        }
    }

    interface Interaction {
        fun onItemSelected(position: Int, item: WodEntity)
    }
}