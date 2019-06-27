package com.example.kotlincurso2

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.Toast

class Int2 : AppCompatActivity() {

    private var btnEjecutar: Button? = null
    private var pbarProgreso: ProgressBar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_int2)


        btnEjecutar = findViewById(R.id.btnEjecutar) as Button
        pbarProgreso = findViewById(R.id.pbarProgreso) as ProgressBar

        btnEjecutar!!.setOnClickListener(View.OnClickListener {
            val msgIntent = Intent(this@Int2, MiIntentService::class.java)
            msgIntent.putExtra("iteraciones", 10)
            startService(msgIntent)
        })

        val filter = IntentFilter()
        filter.addAction(MiIntentService.ACTION_PROGRESO)
        filter.addAction(MiIntentService.ACTION_FIN)
        val rcv = ProgressReceiver()
        registerReceiver(rcv, filter)
    }


    inner class ProgressReceiver : BroadcastReceiver() {

        override fun onReceive(context: Context, intent: Intent) {
            if (intent.action == MiIntentService.ACTION_PROGRESO) {
                val prog = intent.getIntExtra("progreso", 0)
                pbarProgreso?.setProgress(prog)
            } else if (intent.action == MiIntentService.ACTION_FIN) {
                Toast.makeText(this@Int2, "Tarea finalizada!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}