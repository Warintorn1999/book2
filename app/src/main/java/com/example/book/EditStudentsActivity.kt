package com.example.book

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_edit_students.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EditStudentsActivity : AppCompatActivity() {
    val createClient = StudentAPI.create()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_students)

        val mId = intent.getStringExtra("mId")
        val mName = intent.getStringExtra("mName")
        val mWriter = intent.getStringExtra("mWriter")
        val mPub = intent.getStringExtra("mPub")
        val mImg = intent.getStringExtra("mImg")



        edit_id.setText(mId)
        edit_id.isEnabled = false
        edit_name.setText(mName)
        edit_writer.setText(mWriter)
        edit_pub.setText(mPub)
        edit_img.setText(mImg)
    }

    fun saveStudent(v: View) {
        createClient.updateStudent(
            edit_id.text.toString(),
            edit_name.text.toString(),
            edit_writer.text.toString(),
            edit_pub.text.toString(),
            edit_img.text.toString()
        ).enqueue(object : Callback<Student> {

            override fun onResponse(call: Call<Student>, response: Response<Student>) {
                if (response.isSuccessful) {
                    Toast.makeText(
                        applicationContext,
                        "Successfully Updated",
                        Toast.LENGTH_SHORT
                    )
                        .show()
                    finish()
                } else {
                    Toast.makeText(applicationContext, "Error ", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<Student>, t: Throwable) {
                Toast.makeText(
                    applicationContext,
                    "Error onFailure " + t.message,
                    Toast.LENGTH_LONG
                ).show()
            }
        })}

        fun deleteStudent(v: View) {
            val builder = AlertDialog.Builder(this)
            val positiveButtonClick = { dialog: DialogInterface, which: Int ->
                createClient.deleteStudent(edit_id.text.toString())
                    .enqueue(object : Callback<Student> {
                        override fun onResponse(call: Call<Student>, response: Response<Student>) {
                            if (response.isSuccessful()) {
                                Toast.makeText(
                                    applicationContext,
                                    "Successfully Deleted",
                                    Toast.LENGTH_LONG
                                ).show()
                            }
                        }

                        override fun onFailure(call: Call<Student>, t: Throwable) {
                            Toast.makeText(applicationContext, t.message, Toast.LENGTH_LONG).show()
                        }
                    })
                finish()
            }
            val negativeButtonClick = { dialog: DialogInterface, which: Int -> dialog.cancel() }
            builder.setTitle("Warning")
            builder.setMessage("Do you want to delete the student?")
            builder.setNegativeButton("No", negativeButtonClick)
            builder.setPositiveButton("Yes", positiveButtonClick)
            builder.show()
        }



}