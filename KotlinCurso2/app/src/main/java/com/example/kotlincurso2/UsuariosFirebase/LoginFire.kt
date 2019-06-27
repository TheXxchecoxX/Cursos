package com.example.kotlincurso2.UsuariosFirebase

import android.app.ProgressDialog
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.kotlincurso2.R
import com.google.firebase.auth.FirebaseAuth
import java.util.regex.Pattern

class LoginFire : AppCompatActivity() , View.OnClickListener {

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

    private var Name: EditText? = null
    private var Password: EditText? = null
    private var Info: TextView? = null
    private var Login: Button? = null
    private var counter = 5
    private var userRegistration: TextView? = null
    private var firebaseAuth: FirebaseAuth? = null
    private var progressDialog: ProgressDialog? = null
    private var forgotPassword: TextView? = null
    internal lateinit var name:String
    internal lateinit var password:String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_fire)

        setupActionBar()

        Name = findViewById(R.id.etName) as EditText
        Password = findViewById(R.id.etPassword) as EditText
        Info = findViewById(R.id.tvInfo) as TextView
        Login = findViewById(R.id.btnLogin) as Button
        Login!!.setOnClickListener(this)
        userRegistration = findViewById(R.id.tvRegister) as TextView
        forgotPassword = findViewById(R.id.tvForgotPassword) as TextView

        Info!!.setText("Numero de intentos restantes: 5")

        firebaseAuth = FirebaseAuth.getInstance()
        progressDialog = ProgressDialog(this)

        val user = firebaseAuth!!.getCurrentUser()

        if (user != null) {
            //finish();

            startActivity(Intent(this@LoginFire, SecondActivity::class.java))

        }

        Login!!.setOnClickListener(View.OnClickListener {
            //validacion();
            if (validacion()!!) {
                validate(Name!!.getText().toString(), Password!!.getText().toString())
            }
        })

        userRegistration!!.setOnClickListener(View.OnClickListener {
            startActivity(
                Intent(
                    this@LoginFire,
                    RegistrationActivity::class.java
                )
            )
        })

        forgotPassword!!.setOnClickListener(View.OnClickListener {
            startActivity(
                Intent(
                    this@LoginFire,
                    PasswordActivity::class.java
                )
            )
        })
    }

    private fun validate(userName: String, userPassword: String) {

        progressDialog?.setMessage("Espere un momento por favor")
        progressDialog?.show()

        firebaseAuth?.signInWithEmailAndPassword(userName, userPassword)?.addOnCompleteListener { task ->
            if (task.isSuccessful) {
                progressDialog?.dismiss()
                //Toast.makeText(MainActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                checkEmailVerification()
            } else {
                Toast.makeText(this@LoginFire, "Verifique que los datos estén correctos ", Toast.LENGTH_SHORT).show()
                counter--
                Info?.setText("Numero de intentos restantes: $counter")
                progressDialog?.dismiss()
                if (counter == 0) {
                    Login?.setEnabled(false)
                }
            }
        }
    }

    private fun checkEmailVerification() {
        val firebaseUser = firebaseAuth?.getCurrentUser()
        val emailflag = firebaseUser!!.isEmailVerified()

        startActivity(Intent(this@LoginFire, SecondActivity::class.java))

        //        if(emailflag){
        //            finish();
        //            startActivity(new Intent(MainActivity.this, SecondActivity.class));
        //        }else{
        //            Toast.makeText(this, "Verify your email", Toast.LENGTH_SHORT).show();
        //            firebaseAuth.signOut();
        //        }
    }

    private fun validacion(): Boolean? {

        val result = false

        password = Password?.getText().toString()
        name = Name?.getText().toString()

        /*
        if(name.isEmpty() || password.isEmpty()){
            Toast.makeText(this, "Complete los datos por favor", Toast.LENGTH_SHORT).show();
        }else{
            result = true;
        }
        */
        if (name == "") {
            Name?.setError("Requerido")
        } else if (!Patterns.EMAIL_ADDRESS.matcher(name).matches()) {
            Name?.setError("Por favor, introduce una dirección de correo electrónico válida")
            return false
        } else if (password == "") {
            Password?.setError("Requerido")
        } else if (!PASSWORD_PATTERN.matcher(password).matches()) {
            Password?.setError("La contraseña no sigue los parametros ")
            return false
        } else {
            Password?.setError(null)
            return true
        }

        return result
    }

    private fun validatePassword(): Boolean {

        if (password.isEmpty()) {
            Password?.setError("El campo no puede estar vacío")
            return false
        } else if (!PASSWORD_PATTERN.matcher(password).matches()) {
            Password?.setError("Password too weak")
            return false
        } else {
            Password?.setError(null)
            return true
        }
    }

    override fun onClick(v: View) {

    }

    private fun validateEmail(): Boolean {

        if (name.isEmpty()) {
            Name?.setError("El campo no puede estar vacío")
            return false
        } else if (!Patterns.EMAIL_ADDRESS.matcher(name).matches()) {
            Name?.setError("Por favor, introduce una dirección de correo electrónico válida")
            return false
        } else {
            Name?.setError(null)
            return true
        }
    }

    private fun setupActionBar() {
        val actionBar = supportActionBar
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true)
            actionBar.setTitle("Login a Firebase")
        }
    }
}
