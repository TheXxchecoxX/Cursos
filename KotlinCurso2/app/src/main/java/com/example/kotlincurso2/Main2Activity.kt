package com.example.kotlincurso2

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.CheckBox
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main2.*

class Main2Activity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        setupActionBar()

        bthCheckBox.setOnClickListener {
            startActivity(Intent(this, Activity_Checkandradiobuttons::class.java))
        }
        btnbotones.setOnClickListener {
            startActivity(Intent(this, EstilosB::class.java))
        }
        btnLayauts.setOnClickListener {
            startActivity(Intent(this, Activity_ConstraitLayaut::class.java))
        }
        btnRecycle.setOnClickListener {
            startActivity(Intent(this, Recy::class.java))//cambiar
        }
        btnLogin.setOnClickListener {
            startActivity(Intent(this, Login::class.java))
        }
        bthLitView.setOnClickListener {
            startActivity(Intent(this, ActivityListView::class.java))
        }
        bthLista.setOnClickListener {
            startActivity(Intent(this, Activity_DropdownList::class.java))
        }
        btnFregment.setOnClickListener {
            startActivity(Intent(this, FragmentActivity::class.java))
        }
        btnCardView.setOnClickListener {
            startActivity(Intent(this, CardViewActivity::class.java))
        }
        btnFich.setOnClickListener {
            startActivity(Intent(this, MemoriainternaActivity::class.java))
        }
        btnsharep.setOnClickListener {
            startActivity(Intent(this, SharedPreferencetres::class.java))//cambiar
        }
        btnAlerdialog.setOnClickListener {
            startActivity(Intent(this, alert3::class.java))//cambiar
        }
        btnNotificacion.setOnClickListener {
            startActivity(Intent(this, NotificacioesActivity::class.java))//cambiar
        }
        btnHilos.setOnClickListener {
            startActivity(Intent(this, Hilos::class.java))//cambiar
        }
        btnIntetnS.setOnClickListener {
            startActivity(Intent(this, Int2::class.java))//cambiar
        }
        btnLocalizacion.setOnClickListener {
            startActivity(Intent(this, Loc::class.java))//cambiar
        }
        btnGS.setOnClickListener {
            startActivity(Intent(this, GoogleSeg::class.java))//cambiar
        }
        btnTabs.setOnClickListener {
            startActivity(Intent(this, MenuActivity::class.java))//cambiar
        }
    }

    private fun setupActionBar() {
        val actionBar = supportActionBar
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true)
            actionBar.setTitle("Curso Kotlin")
        }
    }
}