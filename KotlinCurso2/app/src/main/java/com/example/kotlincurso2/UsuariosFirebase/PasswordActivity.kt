package com.example.kotlincurso2.UsuariosFirebase

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.kotlincurso2.R
import com.google.firebase.auth.FirebaseAuth

class PasswordActivity : AppCompatActivity() {
    private var passwordEmail: EditText? = null
    private var resetPassword: Button? = null
    private var firebaseAuth: FirebaseAuth? = null
    internal lateinit var email: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_password)


        passwordEmail = findViewById(R.id.etPasswordEmail) as EditText
        resetPassword = findViewById(R.id.btnPasswordReset) as Button
        firebaseAuth = FirebaseAuth.getInstance()

        resetPassword!!.setOnClickListener(View.OnClickListener {
            if (validacion3()?.or(!validateEmail3())!!) {
                val useremail = passwordEmail!!.getText().toString().trim { it <= ' ' }

                if (useremail == "") {
                    Toast.makeText(
                        this@PasswordActivity,
                        "Por favor ingrese su correo electrónico registrado",
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    firebaseAuth!!.sendPasswordResetEmail(useremail).addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            Toast.makeText(
                                this@PasswordActivity,
                                "Correo electrónico de restablecimiento de contraseña enviado!",
                                Toast.LENGTH_SHORT
                            ).show()
                            finish()
                            startActivity(Intent(this@PasswordActivity, LoginFire::class.java))
                        } else {
                            Toast.makeText(
                                this@PasswordActivity,
                                "Error al enviar el email de restablecimiento de contraseña",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                }
            }
        })

    }

    private fun validacion3(): Boolean? {
        var result: Boolean? = false

        email = passwordEmail?.getText().toString()


        if (email.isEmpty()) {
            Toast.makeText(this, "El campo esta vacío ", Toast.LENGTH_SHORT).show()
        } else {
            result = true
        }
        if (email == "") {
            passwordEmail?.setError("Required")
        }

        return result
    }

    private fun validateEmail3(): Boolean {

        if (email.isEmpty()) {
            passwordEmail?.setError("El campo no puede estar vacío")
            return false
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            passwordEmail?.setError("Por favor, introduce una dirección de correo electrónico válida")
            return false
        } else {
            passwordEmail?.setError(null)
            return true
        }
    }
}


