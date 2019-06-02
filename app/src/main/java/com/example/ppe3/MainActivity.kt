package com.example.ppe3

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        signup_button.setOnClickListener{
            var auth= FirebaseAuth.getInstance()
            auth.createUserWithEmailAndPassword(signup_email.text.toString(),
                signup_password.text.toString()).addOnCompleteListener{
                task ->
                    if(task.isSuccessful){
                        Toast.makeText(this,"User Created",
                            Toast.LENGTH_LONG).show()
                        startActivity(Intent(this,LoginActivity::class.java))}

                    else
                      Toast.makeText(this,task.exception?.message,
                          Toast.LENGTH_LONG).show()
            }
        }
    }
}
