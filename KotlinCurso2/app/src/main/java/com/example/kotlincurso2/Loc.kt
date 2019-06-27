package com.example.kotlincurso2

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.*
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.support.v4.app.ActivityCompat
import android.util.Log
import android.widget.TextView
import java.io.IOException
import java.util.*

class Loc : AppCompatActivity() {
    internal lateinit var mensaje1: TextView
    internal lateinit var mensaje2: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_loc)


        mensaje1 = findViewById(R.id.mensaje_id) as TextView
        mensaje2 = findViewById(R.id.mensaje_id2) as TextView
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), 1000)
        } else {
            locationStart()
        }
    }

    private fun locationStart() {
        val mlocManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        val Local = Localizacion()
        Local.setMainActivity(this)
        val gpsEnabled = mlocManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
        if (!gpsEnabled) {
            val settingsIntent = Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
            startActivity(settingsIntent)
        }
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), 1000)
            return
        }
        mlocManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0f, Local as LocationListener)
        mlocManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0f, Local as LocationListener)
        mensaje1.text = "Localización agregada"
        mensaje2.text = ""
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        if (requestCode == 1000) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                locationStart()
                return
            }
        }
    }

    fun setLocation(loc: Location) {
        //Obtener la direccion de la calle a partir de la latitud y la longitud
        if (loc.latitude != 0.0 && loc.longitude != 0.0) {
            try {
                val geocoder = Geocoder(this, Locale.getDefault())
                val list = geocoder.getFromLocation(
                    loc.latitude, loc.longitude, 1
                )
                if (!list.isEmpty()) {
                    val DirCalle = list[0]
                    mensaje2.text = "Mi direccion es: \n" + DirCalle.getAddressLine(0)
                }
            } catch (e: IOException) {
                e.printStackTrace()
            }

        }
    }

    /* Aqui empieza la Clase Localizacion */
    inner class Localizacion : LocationListener {
        internal lateinit var loca: Loc
        fun getMainActivity(): Loc {
            return loca
        }

        fun setMainActivity(loca: Loc) {
            this.loca = loca
        }
        override fun onLocationChanged(loc: Location) {
            // Este metodo se ejecuta cada vez que el GPS recibe nuevas coordenadas
            // debido a la deteccion de un cambio de ubicacion
            loc.latitude
            loc.longitude
            val Text = ("Mi ubicacion actual es: " + "\n Lat = "
                    + loc.latitude + "\n Long = " + loc.longitude)
            mensaje1.text = Text
            this.loca.setLocation(loc)
        }

        override fun onProviderDisabled(provider: String) {
            // Este metodo se ejecuta cuando el GPS es desactivado
            mensaje1.text = "GPS Desactivado"
        }

        override fun onProviderEnabled(provider: String) {
            // Este metodo se ejecuta cuando el GPS es activado
            mensaje1.text = "GPS Activado"
        }

        override fun onStatusChanged(provider: String, status: Int, extras: Bundle) {
            when (status) {
                LocationProvider.AVAILABLE -> Log.d("debug", "LocationProvider.AVAILABLE")
                LocationProvider.OUT_OF_SERVICE -> Log.d("debug", "LocationProvider.OUT_OF_SERVICE")
                LocationProvider.TEMPORARILY_UNAVAILABLE -> Log.d("debug", "LocationProvider.TEMPORARILY_UNAVAILABLE")
            }
        }
    }
}

