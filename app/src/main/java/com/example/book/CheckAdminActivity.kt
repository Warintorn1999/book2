package com.example.book

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_search.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CheckAdminActivity : AppCompatActivity() {

    var statusList = arrayListOf<Status>()
    var CreateClient = StatusAPI.create()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_check_admin)
        callStatusData()

        recycler_view.layoutManager =
            LinearLayoutManager(applicationContext) as RecyclerView.LayoutManager?
        recycler_view.itemAnimator = DefaultItemAnimator() as RecyclerView.ItemAnimator?
        recycler_view.addItemDecoration(
            DividerItemDecoration(
                recycler_view.getContext(),
                DividerItemDecoration.VERTICAL
            )
        )

         recycler_view.addOnItemTouchListener(object : OnItemClickListener {
            override fun onItemClicked(position: Int, view: View) {
                Toast.makeText(
                    applicationContext, "You click on : " + statusList[position].std_id,
                    Toast.LENGTH_SHORT).show()
                val borrow = statusList[position]

                intent.putExtra("sId", borrow.std_id)
                intent.putExtra("sName", borrow.std_name)
                intent.putExtra("sWriter", borrow.Book_name)
                intent.putExtra("sPub", borrow.Date_borrow)
                intent.putExtra("sImg", borrow.Date_return)
                startActivity(intent)
            }

        })
    }
    override fun onResume() {
        super.onResume()
        callStatusData()
    }
    fun clickcheck (v: View) {
        statusList.clear();
        CreateClient.retrieveStatus(edt_search.text.toString())
            .enqueue(object : Callback<Status> {

                override fun onResponse(call: Call<Status>, response: Response<Status>) {
                    statusList.add(
                        Status(
                            response.body()?.std_id.toString(),
                            response.body()?.std_name.toString(),
                            response.body()?.Book_name.toString(),
                            response.body()?.Date_borrow.toString(),
                            response.body()?.Date_return.toString()
                        )
                    )


                }
                override fun onFailure(call: Call<Status>, t: Throwable) = t.printStackTrace()
            })
    }



        fun callStatusData() {

            statusList.clear();
            CreateClient.retrieveStatus()
                .enqueue(object : Callback<List<Status>> {
                    val serv: StatusAPI = Retrofit.Builder()
                        .baseUrl("http://10.0.2.2:3000/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build()
                        .create(StatusAPI::class.java)


                    override fun onResponse(
                        call: Call<List<Status>>,
                        response: Response<List<Status>>
                    ) {
                        response.body()?.forEach {
                            statusList.add(
                                Status(
                                    it.std_id,
                                    it.std_name,
                                    it.Book_name,
                                    it.Date_borrow,
                                    it.Date_return
                                )
                            )
                        }

                        recycler_view.adapter = StatusAdapter(statusList, applicationContext)
                        text1.text = "Student List : " + statusList.size.toString() + " Students"
                    }

                    override fun onFailure(call: retrofit2.Call<List<Status>>, t: Throwable) =
                        t.printStackTrace()

                })
        }
    }


/*interface  OnItemClickListener {
    fun onItemClicked(position: Int, view: View)
}
fun RecyclerView.addOnItemTouchListener(onClickListener: OnItemClickListener) {
    this.addOnChildAttachStateChangeListener(object : RecyclerView.OnChildAttachStateChangeListener {

        override fun onChildViewDetachedFromWindow(view: View) {
            view?.setOnClickListener(null)
        }

        override fun onChildViewAttachedToWindow(view: View) {
            view?.setOnClickListener {
                val holder = getChildViewHolder(view)
                onClickListener.onItemClicked(holder.adapterPosition, view)
            }
        }
    })



}*/