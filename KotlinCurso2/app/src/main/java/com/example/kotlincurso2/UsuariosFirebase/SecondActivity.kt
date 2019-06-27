package com.example.kotlincurso2.UsuariosFirebase

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.example.kotlincurso2.R
import com.google.firebase.auth.FirebaseAuth

class SecondActivity : AppCompatActivity() {
    private var firebaseAuth: FirebaseAuth? = null
    private var logout: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)


        firebaseAuth = FirebaseAuth.getInstance()

        logout = findViewById(R.id.btnLogout) as Button

        logout!!.setOnClickListener(View.OnClickListener { Logout() })
    }

    private fun Logout() {
        firebaseAuth?.signOut()
        finish()
        startActivity(Intent(this@SecondActivity, LoginFire::class.java))
    }
}