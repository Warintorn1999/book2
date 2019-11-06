package com.example.book

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_edit_student_adapter.view.*

class EditStudentAdapter(val items : List<Student>, val context: Context) :
    RecyclerView.Adapter<EditStudentAdapter.ViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val view_item = LayoutInflater.from(parent.context).inflate(R.layout.activity_edit_student_adapter, parent, false)
        return ViewHolder(view_item)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: EditStudentAdapter.ViewHolder, position: Int) {
        holder.tvID?.text = items[position].Book_id
        holder.tvName?.text = items[position].Book_name
    }

    class ViewHolder (view: View) : RecyclerView.ViewHolder(view) {
        val tvID = view.tvID
        val tvName = view.tvName
    }
}
