package com.example.ppe3

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)


        var auth = FirebaseAuth.getInstance()
        home_email.text = "welcome " + auth.currentUser?.email

        home_signout.setOnClickListener {
            auth.signOut()
            startActivity(Intent(this,LoginActivity::class.java))
        }


        home_rapport.setOnClickListener{
            startActivity(Intent(this,mesRapportsActivity::class.java))

        }


        home_calendar.setOnClickListener{
            startActivity(Intent(this,calendrierActivity::class.java))
        }

        rapport.setOnClickListener{
            startActivity(Intent(this,listviewActivity::class.java))
        }

    }

}
