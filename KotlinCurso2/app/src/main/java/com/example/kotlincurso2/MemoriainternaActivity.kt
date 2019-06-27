package com.example.kotlincurso2

import android.app.Activity
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.io.OutputStreamWriter

class MemoriainternaActivity : AppCompatActivity() {
    private var txt_bitacora: EditText? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_memoriainterna)


        txt_bitacora = findViewById<View>(R.id.txt_bitacora) as EditText
        val archivos = fileList()

        if (ArchivosExiste(archivos, "bitacora.txt")) {
            try {
                val archivo = InputStreamReader(openFileInput("bitacora.txt"))
                val br = BufferedReader(archivo)
                var linea: String? = br.readLine()
                var bitacoraCompleta = ""

                while (linea != null) {
                    bitacoraCompleta = bitacoraCompleta + linea + "\n"
                    linea = br.readLine()
                }
                br.close()
                archivo.close()
                txt_bitacora!!.setText(bitacoraCompleta)
            } catch (e: IOException) {

            }

        }
    }

    private fun ArchivosExiste(archivos: Array<String>, NombreArchivo: String): Boolean {
        for (i in archivos.indices)
            if (NombreArchivo == archivos[i])
                return true
        return false
    }

    fun Guardar(view: View) {
        try {
            val archivo = OutputStreamWriter(openFileOutput("bitacora.txt", Activity.MODE_PRIVATE))
            archivo.write(txt_bitacora?.getText().toString())
            archivo.flush()
        } catch (e: IOException) {

        }

        Toast.makeText(this, R.string.bitguardada, Toast.LENGTH_SHORT).show()
        finish()
    }
}

