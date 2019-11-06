package com.example.book

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_main.*

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        login_btn.setOnClickListener{
            var status = if(username_et.text.toString().equals("Admin")
                &&password_et.text.toString().equals("admin1234")) "Logged In Successful" else "LogIn Fail"

            //Toast.makeText(this,status,Toast.LENGTH_SHORT).show()

            login_btn.setOnClickListener(){intent= Intent(this,AdminActivity::class.java)
                startActivity(intent)}
        }
    }
}