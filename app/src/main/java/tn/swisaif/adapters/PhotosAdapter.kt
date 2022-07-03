package tn.swisaif.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import tn.swisaif.R
import tn.swisaif.data.models.PhotosItem

class PhotosAdapter : RecyclerView.Adapter<PhotosAdapter.ViewHolder> {
    var dataList: ArrayList<PhotosItem>
    var clickListener: ItemClickListener

    constructor(dataList: ArrayList<PhotosItem>, clickListener: Any) {
        this.dataList = dataList
        this.clickListener = clickListener as ItemClickListener

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View =
            LayoutInflater.from(parent!!.context).inflate(R.layout.photo_item, parent, false)
        return ViewHolder(
            view,
            clickListener
        )
    }


    // binds the data to the TextView in each row
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = dataList.get(position)

        Picasso.get().load(item.thumbnailUrl)
            .into(holder.photoIv)
    }

    // total number of rows
    override fun getItemCount(): Int {
        return dataList.size
    }

    // stores and recycles views as they are scrolled off screen
    class ViewHolder internal constructor(itemView: View, val clickListener: ItemClickListener) :
        RecyclerView.ViewHolder(itemView), View.OnClickListener {
        var photoIv: ImageView

        override fun onClick(view: View?) {
            clickListener.onItemClick(itemView, adapterPosition)
        }

        init {
            photoIv = itemView.findViewById(R.id.photoIv)

            itemView.setOnClickListener(this)
        }
    }

    interface ItemClickListener {
        fun onItemClick(view: View?, position: Int)
    }

}
