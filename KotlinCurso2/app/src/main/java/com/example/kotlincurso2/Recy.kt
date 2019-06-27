package com.example.kotlincurso2

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.LinearLayout

class Recy : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recy)

        val recyclerView = findViewById(R.id.recyclerView) as RecyclerView

        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL, false)

        val users = ArrayList<UserR>()

        users.add(UserR("Sergio Cruz Martínez", "México, Estado de México"))
        users.add(UserR("jorge Juarez Florez", "México, Estado de México"))
        users.add(UserR("Jorge Juárez Flores", "México, Estado de México"))
        users.add(UserR("Antonio de Valdemar Ruiz ", "México, Estado de México"))
        users.add(UserR("Miguel Angel Cruz Martínez", "México, Estado de México"))
        users.add(UserR("Belal Khan", "Ranchi, India"))
        users.add(UserR("Sergio Cruz Martínez", "México, Estado de México"))
        users.add(UserR("jorge Juarez Florez", "México, Estado de México"))
        users.add(UserR("Jorge Juárez Flores", "México, Estado de México"))
        users.add(UserR("Antonio de Valdemar Ruiz ", "México, Estado de México"))
        users.add(UserR("Miguel Angel Cruz Martínez", "México, Estado de México"))
        users.add(UserR("Belal Khan", "Ranchi, India"))

        val adapter = CustomAdapter(users)

        recyclerView.adapter = adapter

    }
}
