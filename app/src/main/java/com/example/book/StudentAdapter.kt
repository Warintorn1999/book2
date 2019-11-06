package com.example.book

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.std_item_layout.view.*



class StudentAdapter (val items: List<Student>, val context: Context) : RecyclerView.Adapter<ViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view_item = LayoutInflater.from(parent.context).inflate(R.layout.std_item_layout, parent, false)
        return ViewHolder(view_item)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tvID?.text = items[position].Book_id
        holder.tvName?.text = items[position].Book_name
        holder.tvAge?.text = items[position].Writer_name
        holder.tvPub?.text = items[position].Publisher_name.toString()
        Glide.with(context)
            .load(items[position].img)
            .into(holder.tvImg)

    }
}

class ViewHolder (view: View) : RecyclerView.ViewHolder(view) {
    val tvID = view.tv_id
    val tvName = view.tv_name
    val tvAge = view.tv_age
    val tvPub = view.tv_pub
    val tvImg = view.tv_img as ImageView
}
/*class StudentAdapter (val items: List<Student>, val context: Context) : RecyclerView.Adapter<ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view_item = LayoutInflater.from(parent.context).inflate(R.layout.std_item_layout, parent, false)
        return ViewHolder(view_item)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Glide.with(context)
            .load(items[position].img)
            .into(holder.tvImg)

        holder.tvID?.text = items[position].Book_id
        holder.tvName?.text = items[position].Book_name
        holder.tvAge?.text = items[position].Writer_name
        holder.tvPub?.text = items[position].Publisher_name.toString()

    }
}

class ViewHolder (view: View) : RecyclerView.ViewHolder(view) {
    val tvID = view.tv_id
    val tvName = view.tv_name
    val tvAge = view.tv_age
    val tvPub = view.tv_pub
    val tvImg = view.tv_img
}*/