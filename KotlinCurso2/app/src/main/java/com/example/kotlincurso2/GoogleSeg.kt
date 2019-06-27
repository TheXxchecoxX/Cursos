package com.example.kotlincurso2

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.SignInButton
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task

class GoogleSeg : AppCompatActivity() {
    internal var RC_SIGN_IN = 0
    internal lateinit var signInButton: SignInButton
    internal lateinit var mGoogleSignInClient: GoogleSignInClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_google_seg)


        setupActionBar()

        //Inicializando vistas
        signInButton = findViewById(R.id.sbtn) as SignInButton

        //
        //Configure el inicio de sesión para solicitar la ID del usuario, la dirección de correo electrónico y los datos básicos.
        // perfil. El ID y el perfil básico se incluyen en DEFAULT_SIGN_IN.
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .build()

        //Construccion de un GoogleSignInClient con las opciones especificadas por gso.
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso)

        signInButton.setOnClickListener { signIn() }
    }

    private fun signIn() {
        val signInIntent = mGoogleSignInClient.signInIntent
        startActivityForResult(signInIntent, RC_SIGN_IN)
    }

    public override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)


        // Resultado devuelto al iniciar el Intent desde GoogleSignInClient.getSignInIntent (...);
        if (requestCode == RC_SIGN_IN) {
            // La tarea devuelta de esta llamada siempre se completa, no es necesario adjuntarla
            // un listener.
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            handleSignInResult(task)
        }
    }

    private fun handleSignInResult(completedTask: Task<GoogleSignInAccount>) {
        try {
            val account = completedTask.getResult(ApiException::class.java)
            // Firmado con éxito, muestra la interfaz de usuario autenticada.
            startActivity(Intent(this@GoogleSeg, GS2::class.java))
        } catch (e: ApiException) {
            // El código de estado de ApiException indica el motivo detallado del fallo.
            //Consulta la referencia de la clase GoogleSignInStatusCodes para obtener más información.
            Log.w("Google Sign In Error", "signInResult:failed code=" + e.statusCode)
            Toast.makeText(this@GoogleSeg, "Failed ", Toast.LENGTH_LONG).show()
        }

    }

    override fun onStart() {
        // Check for existing Google Sign In account, if the user is already signed in
        // the GoogleSignInAccount will be non-null.
        val account = GoogleSignIn.getLastSignedInAccount(this)
        if (account != null) {
            startActivity(Intent(this@GoogleSeg, GS2::class.java))
        }
        super.onStart()
    }

    private fun setupActionBar() {
        val actionBar = supportActionBar
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true)
            actionBar.setTitle("Conectar a cuenta google")
        }
    }
}

