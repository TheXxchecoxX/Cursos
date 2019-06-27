package com.example.kotlincurso2

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView

class ActivityListView : AppCompatActivity() {
    internal lateinit var lstOpciones: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_view)

        val datos = arrayOf("Elem1", "Elem2", "Elem3", "Elem4", "Elem5")

        val adaptador = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1, datos
        )

        lstOpciones = findViewById(R.id.LstOpciones) as ListView

        lstOpciones.adapter = adaptador

    }
}
