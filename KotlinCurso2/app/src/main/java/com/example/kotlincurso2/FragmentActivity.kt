package com.example.kotlincurso2

import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle

class FragmentActivity : AppCompatActivity(),FregUno.OnFragmentInteractionListener, FregDos.OnFragmentInteractionListener  {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fragment)


    }

    override fun onFragmentInteraction(uri: Uri) {

    }

}
