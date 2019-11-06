package com.example.book

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.status_item_layout.view.*


 class StatusAdapter (val items: List<Status>, val context: Context) : RecyclerView.Adapter<viewHolder>(){

     override fun onCreateviewHolder(parent: ViewGroup, viewType: Int): viewHolder {
        val view_item = LayoutInflater.from(parent.context).inflate(R.layout.status_item_layout, parent, false)
        return viewHolder(view_item)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: viewHolder, position: Int) {
        holder.tvID1?.text = items[position].std_id
        holder.tvName1?.text = items[position].std_name
        holder.tvAge1?.text = items[position].Book_name
        holder.tvDate1?.text = items[position].Date_borrow
        holder.tvPub1?.text = items[position].Date_return

    }
}

class viewHolder (view: View) : RecyclerView.ViewHolder(view) {
    val tvID1 = view.tv_id1
    val tvName1 = view.tv_name1
    val tvAge1 = view.tv_age1
    val tvDate1 = view.tv_date1
    val tvPub1 = view.tv_pub1

}