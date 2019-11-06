package com.example.book

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_search.*
import retrofit2.Callback
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class SearchActivity : AppCompatActivity() {

    var studentList = arrayListOf<Student>()
    var createClient = StudentAPI.create()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)
        callStudentData()

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
                    applicationContext, "You click on : " + studentList[position].Book_id,
                    Toast.LENGTH_SHORT).show()
                val std = studentList[position]
                val intent = Intent(applicationContext, EditStudentsActivity::class.java)
                intent.putExtra("mId", std.Book_id)
                intent.putExtra("mName", std.Book_name)
                intent.putExtra("mWriter", std.Writer_name)
                intent.putExtra("mPub", std.Publisher_name)
                intent.putExtra("mImg", std.img)
                startActivity(intent)
            }

        })
    }
    override fun onResume() {
        super.onResume()
        callStudentData()
    }
    fun clickSearch(v: View) {
        studentList.clear();
        createClient.retrieveStudent(edt_search.text.toString())
            .enqueue(object : Callback<Student> {

                override fun onResponse(call: Call<Student>, response: Response<Student>) {
                    studentList.add(Student(
                            response.body()?.Book_id.toString(),
                            response.body()?.Book_name.toString(),
                            response.body()?.Writer_name.toString(),
                            response.body()?.Publisher_name.toString(),
                            response.body()?.img.toString()
                        )
                    )

                    recycler_view.adapter = EditStudentAdapter(studentList, applicationContext)
                }

                override fun onFailure(call: Call<Student>, t: Throwable) = t.printStackTrace()
            })
    }




   /* override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.option_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        when (id) {
            R.id.item1 -> {
                val intent = Intent(this@SearchActivity, InsertActivity::class.java)
                startActivity(intent)
                return true
            }

            else -> return super.onOptionsItemSelected(item)
        }
    }*/
fun callStudentData(){

      studentList.clear();
      createClient.retrieveStudent()
          .enqueue(object : Callback<List<Student>> {
              val serv : StudentAPI = Retrofit.Builder()
                  .baseUrl("http://10.0.2.2:3000/")
                  .addConverterFactory(GsonConverterFactory.create())
                  .build()
                  .create(StudentAPI ::class.java)


              override fun onResponse(
                  call: Call<List<Student>>,
                  response: Response<List<Student>>
              ) {
                  response.body()?.forEach{
                      studentList.add(Student(it.Book_id, it.Book_name, it.Writer_name,it.Publisher_name,it.img))
                  }

                  recycler_view.adapter = StudentAdapter(studentList,applicationContext)
                  text1.text = "Student List : "+ studentList.size.toString()+ " Students"
              }

              override fun onFailure(call: retrofit2.Call<List<Student>>, t: Throwable) = t.printStackTrace()

          })
  }
}

interface  OnItemClickListener {
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



}