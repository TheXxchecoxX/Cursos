package com.example.kotlincurso2

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.*
import com.example.kotlincurso.model.Persona
import com.google.firebase.FirebaseApp
import com.google.firebase.database.*
import java.util.*
import java.util.regex.Pattern

class FirebaseActivity : AppCompatActivity() {

    private val listPerson = ArrayList<Persona>()
    internal lateinit var arrayAdapterPersona: ArrayAdapter<Persona>

    internal lateinit var nomP: EditText
    internal lateinit var appP: EditText
    internal lateinit var correoP: EditText
    internal lateinit var passwordP: EditText
    internal lateinit var listV_personas: ListView

    internal lateinit var firebaseDatabase: FirebaseDatabase
    internal lateinit var databaseReference: DatabaseReference

    internal lateinit var personaSelected: Persona

    internal var password:String? = null
    internal var name:String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_firebase)


        nomP = findViewById(R.id.txt_nombrePersona)
        appP = findViewById(R.id.txt_appPersona)
        correoP = findViewById(R.id.txt_correoPersona)
        passwordP = findViewById(R.id.txt_passwordPersona)

        listV_personas = findViewById(R.id.lv_datosPersonas)
        inicializarFirebase()
        listarDatos()

        listV_personas.onItemClickListener = AdapterView.OnItemClickListener { parent, view, position, id ->
            personaSelected = parent.getItemAtPosition(position) as Persona
            nomP.setText(personaSelected.getNombre())
            appP.setText(personaSelected.getApellido())
            correoP.setText(personaSelected.getCorreo())
            passwordP.setText(personaSelected.getPassword())
        }

    }

    private fun listarDatos() {
        databaseReference.child("Persona").addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                listPerson.clear()
                for (objSnaptshot in dataSnapshot.children) {
                    val p = objSnaptshot.getValue(Persona::class.java)
                    if (p != null) {
                        listPerson.add(p)
                    }

                    arrayAdapterPersona =
                        ArrayAdapter(this@FirebaseActivity, android.R.layout.simple_list_item_1, listPerson)
                    listV_personas.adapter = arrayAdapterPersona
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {

            }
        })
    }

    private fun inicializarFirebase() {
        FirebaseApp.initializeApp(this)
        firebaseDatabase = FirebaseDatabase.getInstance()
        //firebaseDatabase.setPersistenceEnabled(true);
        databaseReference = firebaseDatabase.reference
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main2, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        val nombre = nomP.text.toString()
        val correo = correoP.text.toString()
        val password = passwordP.text.toString()
        val app = appP.text.toString()


        when (item.itemId) {
            R.id.icon_add -> {
                if (nombre == "" || correo == "" || password == "" || app == "") {
                    validacion()
                } else {
                    val p = Persona()
                    p.setUid(UUID.randomUUID().toString())
                    p.setNombre(nombre)
                    p.setApellido(app)
                    p.setCorreo(correo)
                    p.setPassword(password)
                    p.getUid()?.let { databaseReference.child("Persona").child(it).setValue(p) }
                    Toast.makeText(this, "Agregado", Toast.LENGTH_LONG).show()
                    limpiarCajas()
                }
            }
            R.id.icon_save -> {
                if (nombre == "" || correo == "" || password == "" || app == "") {
                    validacionmod()
                } else {
                    val p = Persona()
                    personaSelected.getUid()?.let { p.setUid(it) }
                    p.setNombre(nomP.text.toString().trim { it <= ' ' })
                    p.setApellido(appP.text.toString().trim { it <= ' ' })
                    p.setCorreo(correoP.text.toString().trim { it <= ' ' })
                    p.setPassword(passwordP.text.toString().trim { it <= ' ' })
                    p.getUid()?.let { databaseReference.child("Persona").child(it).setValue(p) }
                    Toast.makeText(this, "Actualizado", Toast.LENGTH_LONG).show()
                    limpiarCajas()
                }
            }
            R.id.icon_delete -> {
                if (nombre == "" || correo == "" || password == "" || app == "") {
                    validacionmod()
                } else {
                    val p = Persona()
                    personaSelected.getUid()?.let { p.setUid(it) }
                    p.getUid()?.let { databaseReference.child("Persona").child(it).removeValue() }
                    Toast.makeText(this, "Eliminado", Toast.LENGTH_LONG).show()
                    limpiarCajas()
                }
            }
            else -> {
            }
        }
        return true
    }

    private fun limpiarCajas() {
        nomP.setText("")
        correoP.setText("")
        passwordP.setText("")
        appP.setText("")
    }

    private fun validacion() {
        val nombre = nomP.text.toString()
        val correo = correoP.text.toString()
        val password = passwordP.text.toString()
        val app = appP.text.toString()
        if (nombre == "") {
            nomP.error = "Requerido"
        } else if (app == "") {
            appP.error = "Requerido"
        } else if (correo == "") {
            correoP.error = "Requerido"
        } else if (password == "") {
            passwordP.error = "Requerido"
        }
    }

    private fun validacionmod() {
        val nombre = nomP.text.toString()
        val correo = correoP.text.toString()
        val password = passwordP.text.toString()
        val app = appP.text.toString()

        if (nombre.isEmpty() || correo.isEmpty() || password.isEmpty() || app.isEmpty()) {
            Toast.makeText(this, "Selecciona primero a una persona", Toast.LENGTH_SHORT).show()
        }
    }


}
