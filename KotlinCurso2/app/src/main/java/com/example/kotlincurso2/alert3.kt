package com.example.kotlincurso2

import android.content.DialogInterface
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog

class alert3 : AppCompatActivity() {
    override fun onBackPressed() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("¡Estás seguro!")
        builder.setMessage("¿Quieres salir de  la pantalla?")
        builder.setPositiveButton("Si",{ dialogInterface: DialogInterface, i: Int ->
            finish()
        })
        builder.setNegativeButton("No",{ dialogInterface: DialogInterface, i: Int -> })
        builder.show()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_alert3)
    }
}
