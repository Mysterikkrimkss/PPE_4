package com.example.ppe3

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.snapshot.EmptyNode.Empty
import kotlinx.android.synthetic.main.activity_login.*


class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val login = login_email.text.toString().trim()
        val pass = login_password.text.toString().trim()

        login_button.setOnClickListener{
            var auth = FirebaseAuth.getInstance()
            auth.signInWithEmailAndPassword(login_email.text.toString(),
                login_password.text.toString()).addOnCompleteListener{
                task -> if(task.isSuccessful)
                        startActivity(Intent(this,HomeActivity::class.java))
                else
                    Toast.makeText(this,"Login Fail", Toast.LENGTH_LONG).show()

            }
        }









        login_register.setOnClickListener{
            startActivity(Intent(this,MainActivity::class.java))

        }
    }
}
