package com.example.kotlincurso.model

class Persona {

    private var uid: String? = null
    private var Nombre: String? = null
    private var Apellido: String? = null
    private var Correo: String? = null
    private var Password: String? = null

    fun getUid(): String? {
        return uid
    }

    fun setUid(uid: String) {
        this.uid = uid
    }

    fun getNombre(): String? {
        return Nombre
    }

    fun setNombre(nombre: String) {
        Nombre = nombre
    }

    fun getApellido(): String? {
        return Apellido
    }

    fun setApellido(apellido: String) {
        Apellido = apellido
    }

    fun getCorreo(): String? {
        return Correo
    }

    fun setCorreo(correo: String) {
        Correo = correo
    }

    fun getPassword(): String? {
        return Password
    }

    fun setPassword(password: String) {
        Password = password
    }

    override fun toString(): String {

        return Nombre.toString()
    }
}
