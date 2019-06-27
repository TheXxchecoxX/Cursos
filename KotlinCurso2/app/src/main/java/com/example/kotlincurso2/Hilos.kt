package com.example.kotlincurso2

import android.os.AsyncTask
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.Toast

class Hilos : AppCompatActivity(), View.OnClickListener  {
    internal lateinit var button: Button
    internal lateinit var button2: Button
    internal lateinit var button3: Button
    internal lateinit var progressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hilos)


        button = findViewById<View>(R.id.button) as Button
        button2 = findViewById<View>(R.id.button2) as Button
        button3 = findViewById<View>(R.id.button3) as Button
        progressBar = findViewById<View>(R.id.progressBar) as ProgressBar

        button.setOnClickListener(this)
        button2.setOnClickListener(this)
        button3.setOnClickListener(this)


    }

    private fun UnSegundo() {
        try {
            Thread.sleep(1000)
        } catch (e: InterruptedException) {
        }

    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.button -> for (i in 1..10) {
                UnSegundo()
            }
            R.id.button2 -> Hilos()
            R.id.button3 -> {
                val ejemploAsyncTask = EjemploAsyncTask()
                ejemploAsyncTask.execute()
            }
            else -> {
            }
        }
    }

    internal fun Hilos() {
        Thread(Runnable {
            for (i in 1..10) {
                UnSegundo()
            }
            runOnUiThread { Toast.makeText(this, "Tarea larga finalizada", Toast.LENGTH_SHORT).show() } //Toast.makeText(baseContext, "Tarea larga finalizada", Toast.LENGTH_SHORT).show()
        }).start()
    }

    private inner class EjemploAsyncTask : AsyncTask<Void, Int, Boolean>() {
        override fun onPreExecute() {
            super.onPreExecute()
            progressBar.progress = 0
            progressBar.max = 100
        }

        override fun doInBackground(vararg voids: Void): Boolean? {

            for (i in 1..10) {
                UnSegundo()
                publishProgress(i * 10)
                if (isCancelled) {
                    break
                }
            }

            return true
        }

        protected override fun onProgressUpdate(vararg values: Int?) {
            super.onProgressUpdate(*values)

            progressBar.progress = values[0]!!

        }

        override fun onPostExecute(resultado: Boolean?) {
            //super.onPostExecute(aVoid);
            if (resultado!!) {
                Toast.makeText(baseContext, "Tarea larga finalizada en AsyncTask", Toast.LENGTH_SHORT).show()
            }
        }

        override fun onCancelled() {
            super.onCancelled()
            Toast.makeText(baseContext, "Tarea larga ha sido cancelada  ", Toast.LENGTH_SHORT).show()
        }

    }

}
