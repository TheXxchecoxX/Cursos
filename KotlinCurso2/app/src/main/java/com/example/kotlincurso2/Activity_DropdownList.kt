package com.example.kotlincurso2

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.TextView

class Activity_DropdownList : AppCompatActivity() {
    private var lblMensaje: TextView? = null
    private var cmbOpciones: Spinner? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity__dropdown_list)
        lblMensaje = findViewById<View>(R.id.LblMensaje) as TextView?
        cmbOpciones = findViewById<View>(R.id.CmbOpciones) as Spinner?

        val datos = arrayOf("Elem1", "Elem2", "Elem3", "Elem4", "Elem5")

        //Alternativa 1: Array java
        val adaptador = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_item, datos
        )


        adaptador.setDropDownViewResource(
            android.R.layout.simple_spinner_dropdown_item
        )

        cmbOpciones?.setAdapter(adaptador)

        cmbOpciones?.setOnItemSelectedListener(
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>,
                    v: android.view.View, position: Int, id: Long
                ) {
                    lblMensaje?.setText("Seleccionado: " + parent.getItemAtPosition(position))
                }

                override fun onNothingSelected(parent: AdapterView<*>) {
                    lblMensaje?.setText("")
                }
            })
    }
}
