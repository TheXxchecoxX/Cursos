package com.example.kotlincurso2.UsuariosFirebase

import android.content.Intent
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.view.View
import android.widget.*
import com.example.kotlincurso2.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import java.util.regex.Pattern

class RegistrationActivity : AppCompatActivity() {
    private val PASSWORD_PATTERN = Pattern.compile(
        "^" +
                "(?=.*[0-9])" +         //at least 1 digit

                "(?=.*[a-z])" +         //at least 1 lower case letter

                "(?=.*[A-Z])" +         //at least 1 upper case letter

                //"(?=.*[a-zA-Z])" +      //any letter
                //"(?=.*[@#$%^&+=])" +    //at least 1 special character
                //"(?=\\S+$)" +           //no white spaces
                ".{8,}" +               //at least 8 characters

                "$"
    )

    private var userName: EditText? = null
    private var userPassword: EditText? = null
    private var userEmail: EditText? = null
    private var userAge: EditText? = null
    private var regButton: Button? = null
    private var userLogin: TextView? = null
    private var firebaseAuth: FirebaseAuth? = null
    private val userProfilePic: ImageView? = null
    internal lateinit var email:String
    internal lateinit var name:String
    internal lateinit var age:String
    internal lateinit var password:String
    private var firebaseStorage: FirebaseStorage? = null
    //private static int PICK_IMAGE = 123;
    internal var imagePath: Uri? = null
    private var storageReference: StorageReference? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)


        setupUIViews()

        firebaseAuth = FirebaseAuth.getInstance()
        firebaseStorage = FirebaseStorage.getInstance()

        storageReference = firebaseStorage!!.getReference()


        regButton?.setOnClickListener(View.OnClickListener {
            if (validate2()!!) {
                //Upload data to the database
                val user_email = userEmail?.getText().toString().trim { it <= ' ' }
                val user_password = userPassword?.getText().toString().trim { it <= ' ' }

                firebaseAuth!!.createUserWithEmailAndPassword(user_email, user_password).addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        //sendEmailVerification();
                        //sendUserData();
                        firebaseAuth!!.signOut()
                        Toast.makeText(
                            this@RegistrationActivity,
                            "Registrado con éxito, carga completa!",
                            Toast.LENGTH_SHORT
                        ).show()
                        finish()
                        startActivity(Intent(this@RegistrationActivity, LoginFire::class.java))
                    } else {
                        Toast.makeText(this@RegistrationActivity, "Registro fallido", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        })

        userLogin?.setOnClickListener(View.OnClickListener {
            startActivity(
                Intent(
                    this@RegistrationActivity,
                    LoginFire::class.java
                )
            )
        })

    }

    private fun setupUIViews() {
        userName = findViewById(R.id.etUserName) as EditText
        userPassword = findViewById(R.id.etUserPassword) as EditText
        userEmail = findViewById(R.id.etUserEmail) as EditText
        regButton = findViewById(R.id.btnRegister) as Button
        userLogin = findViewById(R.id.tvUserLogin) as TextView
        userAge = findViewById(R.id.etAge) as EditText
        //userProfilePic = (ImageView)findViewById(R.id.ivProfile);
    }

    private fun validate2(): Boolean? {
        var result: Boolean? = false

        name = userName?.getText().toString()
        password = userPassword?.getText().toString()
        email = userEmail?.getText().toString()
        age = userAge?.getText().toString()

        if (name.isEmpty() || password.isEmpty() || email.isEmpty() || age.isEmpty()) {
            Toast.makeText(this, "Complete los datos por favor", Toast.LENGTH_SHORT).show()
        } else {
            result = true
        }
        if (name == "") {
            userName?.setError("Required")
        } else if (email == "") {
            userEmail?.setError("Required")
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            userEmail?.setError("Por favor, introduce una dirección de correo electrónico válida")
            return false
        } else if (password == "") {
            userPassword?.setError("Required")
        } else if (!PASSWORD_PATTERN.matcher(password).matches()) {
            userPassword?.setError("La contraseña debe teer al menos una Mayuscula una minuscula, 1 numero y contar con 8 caracteres")
            return false
        } else if (age == "") {
            userAge?.setError("Required")
        } else {
            userPassword?.setError(null)
            userEmail?.setError(null)
            return true
        }
        return result
    }


    private fun sendEmailVerification() {
        val firebaseUser = firebaseAuth?.getCurrentUser()
        firebaseUser?.sendEmailVerification()?.addOnCompleteListener { task ->
            if (task.isSuccessful) {
                //sendUserData();
                Toast.makeText(
                    this@RegistrationActivity,
                    "Successfully Registered, Verification mail sent!",
                    Toast.LENGTH_SHORT
                ).show()
                firebaseAuth?.signOut()
                finish()
                startActivity(Intent(this@RegistrationActivity, LoginFire::class.java))
            } else {
                Toast.makeText(
                    this@RegistrationActivity,
                    "El correo de verificación no ha sido enviado!",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

}

