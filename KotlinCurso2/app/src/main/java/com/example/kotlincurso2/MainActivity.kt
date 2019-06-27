package com.example.kotlincurso2

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.design.widget.Snackbar
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.view.MenuItem
import android.support.v4.widget.DrawerLayout
import android.support.design.widget.NavigationView
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.widget.Toast
import com.example.kotlincurso2.UsuariosFirebase.LoginFire

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener, RedFragment.OnFragmentInteractionListener,
    GreenFragment.OnFragmentInteractionListener   {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        val fab: FloatingActionButton = this.findViewById(R.id.fab)
        fab.setOnClickListener { view ->
            Snackbar.make(view, "Hola esto es un Snackbar", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)
        val toggle = ActionBarDrawerToggle(
            this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        navView.setNavigationItemSelectedListener(this)
    }

    override fun onBackPressed() {
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        var miFragment: Fragment? = null
        var fragmentSeleccionado = false

        when (item.itemId) {
            R.id.nav_gallery -> {
                miFragment = RedFragment()
                fragmentSeleccionado = true
            }
            R.id.nav_slideshow -> {
                miFragment = GreenFragment()
                fragmentSeleccionado = true
            }
            R.id.nav_share -> {
                startActivity(Intent(this, Main2Activity::class.java))
            }
            R.id.Firebase -> {
                startActivity(Intent(this, FirebaseActivity::class.java))
            }
            R.id.nav_send -> {
                startActivity(Intent(this, LoginFire::class.java))
            }
        }

        if (fragmentSeleccionado == true) {
            if (miFragment != null) {
                supportFragmentManager.beginTransaction().replace(R.id.content_main, miFragment).commit()
            }
        }


        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    override fun onFragmentInteraction(uri: Uri) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onStart() {
        super.onStart()
        Toast.makeText(this, "OnStart", Toast.LENGTH_SHORT).show()
        // La actividad está a punto de hacerse visible.
    }

    override fun onResume() {
        super.onResume()
        Toast.makeText(this, "OnResume", Toast.LENGTH_SHORT).show()
        // La actividad se ha vuelto visible (ahora se "reanuda").
    }

    override fun onPause() {
        super.onPause()
        Toast.makeText(this, "OnPause", Toast.LENGTH_SHORT).show()
        // Enfocarse en otra actividad  (esta actividad está a punto de ser "detenida").
    }

    override fun onStop() {
        super.onStop()
        Toast.makeText(this, "OnStop", Toast.LENGTH_SHORT).show()
        // La actividad ya no es visible (ahora está "detenida")
    }

    override fun onDestroy() {
        super.onDestroy()
        Toast.makeText(this, "OnDestroy", Toast.LENGTH_SHORT).show()
        // La actividad está a punto de ser destruida.
    }

}
