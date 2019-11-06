package com.example.book

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_admin.*
import kotlinx.android.synthetic.main.activity_login.*

class AdminActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin)

        login_Addbook.setOnClickListener(){intent= Intent(this,InsertActivity::class.java)
            startActivity(intent)}
        login_search.setOnClickListener(){intent= Intent(this,SearchActivity::class.java)
            startActivity(intent)}
        check_status.setOnClickListener(){intent= Intent(this,CheckAdminActivity::class.java)
            startActivity(intent)}

       /* login_check.setOnClickListener(){intent= Intent(this,SearchActivity::class.java)
            startActivity(intent)}*/
    }
    /*override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        when (id) {
            R.id.item1 -> {
                val intent = Intent(this@AdminActivity, InsertActivity::class.java)
                startActivity(intent)
                return true
            }

            else -> return super.onOptionsItemSelected(item)
        }
    }*/
}
