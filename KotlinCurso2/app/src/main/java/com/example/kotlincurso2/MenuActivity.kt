package com.example.kotlincurso2

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TabHost

class MenuActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)


        val res = resources
        val tabs = findViewById<View>(android.R.id.tabhost) as TabHost
        tabs.setup()

        var spec: TabHost.TabSpec = tabs.newTabSpec("mitab1")
        spec.setContent(R.id.tab1)
        spec.setIndicator(
            "",
            res.getDrawable(android.R.drawable.ic_menu_preferences)
        )
        tabs.addTab(spec)

        spec = tabs.newTabSpec("mitab2")
        spec.setContent(R.id.tab2)
        spec.setIndicator(
            "",
            res.getDrawable(android.R.drawable.ic_menu_compass)
        )
        tabs.addTab(spec)

        spec = tabs.newTabSpec("mitab3")
        spec.setContent(R.id.tab3)
        spec.setIndicator(
            "",
            res.getDrawable(android.R.drawable.ic_menu_agenda)
        )
        tabs.addTab(spec)

        tabs.currentTab = 0
    }
}
