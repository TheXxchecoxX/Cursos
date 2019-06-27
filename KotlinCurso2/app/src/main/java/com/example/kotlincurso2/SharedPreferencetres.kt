package com.example.kotlincurso2

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class SharedPreferencetres : AppCompatActivity() {
    lateinit var editTextName : EditText
    lateinit var editTextCity : EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shared_preferencetres)


        editTextName = findViewById(R.id.editTextName)
        editTextCity = findViewById(R.id.editTextCity)

        retrieveData()

        findViewById<Button>(R.id.buttonSP3).setOnClickListener {
            saveData()

        }
    }

    private fun retrieveData(){
        val mypref = getSharedPreferences("mypref", Context.MODE_PRIVATE)
        val name = mypref.getString("name", "")
        val cyty = mypref.getString("cyty" , "")

        editTextName.setText(name)
        editTextCity.setText(cyty)

        // mypref.getString()
    }

    private fun saveData() {
        if(editTextName.text.isEmpty()){
            editTextName?.setError("Requerido")
            return
        }
        if(editTextCity.text.isEmpty()){
            editTextCity?.setError("Requerido")
            return
        }

        val mypref = getSharedPreferences("mypref", Context.MODE_PRIVATE)

        val editor = mypref.edit()

        editor.putString("name",editTextName.text.toString())
        editor.putString("cyty",editTextCity.text.toString())

        editor.apply()

        Toast.makeText(this, "Dato guardado", Toast.LENGTH_LONG).show()
    }
}
