package com.example.kotlincurso2

import android.app.IntentService
import android.content.Intent


class MiIntentService : IntentService("MiIntentService") {

    override fun onHandleIntent(intent: Intent?) {
        val iter = intent!!.getIntExtra("iteraciones", 0)

        for (i in 1..iter) {
            tareaLarga()

            //Comunicamos el progreso
            val bcIntent = Intent()
            bcIntent.action = ACTION_PROGRESO
            bcIntent.putExtra("progreso", i * 10)
            sendBroadcast(bcIntent)
        }

        val bcIntent = Intent()
        bcIntent.action = ACTION_FIN
        sendBroadcast(bcIntent)
    }

    private fun tareaLarga() {
        try {
            Thread.sleep(1000)
        } catch (e: InterruptedException) {
        }

    }

    companion object {

        val ACTION_PROGRESO = "com.example.cursoandroidjava.action.PROGRESO"
        val ACTION_FIN = "com.example.cursoandroidjava.action.FIN"
    }
}
