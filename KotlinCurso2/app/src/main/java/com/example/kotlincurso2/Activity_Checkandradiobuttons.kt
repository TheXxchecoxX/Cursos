package com.example.kotlincurso2

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.CheckBox
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView

class Activity_Checkandradiobuttons : AppCompatActivity() {
    private var lblMensaje: TextView? = null
    private var rbOpcion1: RadioButton? = null
    private var rbOpcion2: RadioButton? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity__checkandradiobuttons)

        lblMensaje = findViewById(R.id.LblSeleccion) as TextView
        rbOpcion1 = findViewById(R.id.RbOpcion1) as RadioButton
        rbOpcion2 = findViewById(R.id.RbOpcion2) as RadioButton

        val list = View.OnClickListener { view ->
            var opcion = ""
            when (view.id) {
                R.id.RbOpcion1 -> opcion = "opción 1"
                R.id.RbOpcion2 -> opcion = "opción 2"
            }

            lblMensaje!!.setText("ID opción seleccionada: $opcion")
        }

        //para iniciar el texto del tesxView
        rbOpcion1!!.setOnClickListener(list)
        rbOpcion2!!.setOnClickListener(list)

        val rgOpciones = findViewById(R.id.GrbGrupo1) as RadioGroup
        rgOpciones.setOnCheckedChangeListener { group, checkedId ->
            var opcion = ""
            when (checkedId) {
                R.id.RbOpcion1 -> opcion = "opción 1"
                R.id.RbOpcion2 -> opcion = "opción 2"
            }

            lblMensaje!!.setText("ID opción seleccionada: $opcion")
        }

        val cbMarcame = findViewById(R.id.ChkMarcame) as CheckBox

        cbMarcame.setOnClickListener { view ->
            val isChecked = (view as CheckBox).isChecked

            if (isChecked) {
                cbMarcame.text = "Checkbox marcado!"
            } else {
                cbMarcame.text = "Checkbox desmarcado!"
            }
        }

        val rg = findViewById(R.id.GrbGrupo1) as RadioGroup
        rg.clearCheck()
        rg.check(R.id.RbOpcion1)
        val idSeleccionado = rg.checkedRadioButtonId

    }
}

