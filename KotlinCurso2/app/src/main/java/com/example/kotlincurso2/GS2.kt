package com.example.kotlincurso2

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions

class GS2 : AppCompatActivity() {
    internal lateinit var mGoogleSignInClient: GoogleSignInClient
    internal lateinit var sign_out: Button
    internal lateinit var nameTV: TextView
    internal lateinit var emailTV: TextView
    internal lateinit var idTV: TextView
    internal lateinit var photoIV: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gs2)


        sign_out = findViewById(R.id.log_out)
        nameTV = findViewById(R.id.name)
        emailTV = findViewById(R.id.email)
        idTV = findViewById(R.id.id)
        photoIV = findViewById(R.id.photo)

        // Configure el inicio de sesión para solicitar la ID del usuario, la dirección de correo electrónico y los datos básicos.
        // perfil. El ID y el perfil básico se incluyen en DEFAULT_SIGN_IN.
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .build()

        // Construccion de un GoogleSignInClient con las opciones especificadas por gso.
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso)

        val acct = GoogleSignIn.getLastSignedInAccount(this@GS2)
        if (acct != null) {
            val personName = acct.displayName
            val personGivenName = acct.givenName
            val personFamilyName = acct.familyName
            val personEmail = acct.email
            val personId = acct.id
            val personPhoto = acct.photoUrl

            nameTV.text = "Name: " + personName!!
            emailTV.text = "Email: " + personEmail!!
            idTV.text = "ID: " + personId!!
            Glide.with(this).load(personPhoto).into(photoIV)
        }

        sign_out.setOnClickListener { signOut() }
    }

    private fun signOut() {
        mGoogleSignInClient.signOut()
            .addOnCompleteListener(this) {
                Toast.makeText(this@GS2, "Se ha cerrado correctamente", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this@GS2, GoogleSeg::class.java))
                finish()
            }
    }
}
