package tn.swisaif.adapters;

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import tn.swisaif.R
import tn.swisaif.data.models.AlbumsItem
import tn.swisaif.data.models.UsersItem


class AlbumsAdapter : RecyclerView.Adapter<AlbumsAdapter.ViewHolder> {
    var dataList: ArrayList<AlbumsItem>
    var auteurs: ArrayList<UsersItem>
    var clickListener: ItemClickListener

    constructor(
        dataList: ArrayList<AlbumsItem>,
        auteurs: ArrayList<UsersItem>,
        clickListener: Any
    ) {
        this.dataList = dataList
        this.auteurs = auteurs
        this.clickListener = clickListener as ItemClickListener

    }

    // inflates the row layout from xml when needed
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View =
            LayoutInflater.from(parent!!.context).inflate(R.layout.album_item, parent, false)
        return ViewHolder(
            view,
            clickListener
        )
    }


    // binds the data to the TextView in each row
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = dataList.get(position)
        holder.albumTv.text = item.title
        holder.auteurTv.text = auteurs.find { it.id == item.userId }?.name

    }

    // total number of rows
    override fun getItemCount(): Int {
        return dataList.size
    }

    // stores and recycles views as they are scrolled off screen
    class ViewHolder internal constructor(itemView: View, val clickListener: ItemClickListener) :
        RecyclerView.ViewHolder(itemView), View.OnClickListener {
        var albumTv: TextView
        var auteurTv: TextView

        override fun onClick(view: View?) {
            clickListener.onItemClick(itemView, adapterPosition)
        }

        init {
            albumTv = itemView.findViewById(R.id.albumTv)
            auteurTv = itemView.findViewById(R.id.auteurTv)

            itemView.setOnClickListener(this)
        }
    }

    interface ItemClickListener {
        fun onItemClick(view: View?, position: Int)
    }

}
