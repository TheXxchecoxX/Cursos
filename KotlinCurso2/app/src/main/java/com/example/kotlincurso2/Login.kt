package com.example.kotlincurso2

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import java.util.regex.Pattern

class Login : AppCompatActivity(), View.OnClickListener {
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

    private var editText1: EditText? = null
    private var editText2: EditText? = null
    private var buttonLA: Button? = null
    internal var usurio:String = ""
    internal var contra:String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)


        editText1 = findViewById(R.id.editText1) as EditText
        editText2 = findViewById(R.id.editText2) as EditText
        buttonLA = findViewById(R.id.buttonLA) as Button
        buttonLA!!.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        //String usurio = editText1.getText().toString();
        //String contra = editText2.getText().toString();

        if (v.id == R.id.buttonLA) {
            if (validacionLA()!!) {

                if (usurio == "login" && contra == "Aa12Bb12") {
                    Toast.makeText(this, "Dato correcto", Toast.LENGTH_LONG).show()
                    val i = Intent(this, Main2Activity::class.java)
                    super.startActivity(i)
                } else {
                    Toast.makeText(this, "Verificar que el usuario y contraseña estén correctos", Toast.LENGTH_LONG)
                        .show()
                }

            }
        }
    }

    private fun validacionLA(): Boolean? {
        val result = false

        usurio = editText1?.getText().toString()
        contra = editText2?.getText().toString()

        /*
        if(name.isEmpty() || password.isEmpty()){
            Toast.makeText(this, "Complete los datos por favor", Toast.LENGTH_SHORT).show();
        }else{
            result = true;
        }
        */
        if (usurio == "") {
            editText1?.setError("Requerido")
        } else if (contra == "") {
            editText2?.setError("Requerido")
        } else if (!PASSWORD_PATTERN.matcher(contra).matches()) {
            editText2?.setError("La contraseña es incorrecta o los parametros estan mal")
            return false
        } else {
            editText2?.setError(null)
            return true
        }

        return result
    }
}
