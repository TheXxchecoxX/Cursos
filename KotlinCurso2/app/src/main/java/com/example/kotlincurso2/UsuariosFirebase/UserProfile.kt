package com.example.kotlincurso2.UsuariosFirebase

class UserProfile {
    lateinit var userAge: String
    lateinit var userEmail: String
    lateinit var userName: String

    fun UserProfile() {

    }

    fun UserProfile(userAge: String, userEmail: String, userName: String) {
        this.userAge = userAge
        this.userEmail = userEmail
        this.userName = userName
    }

    internal fun getUserAge(): String {
        return userAge
    }

    internal fun setUserAge(userAge: String) {
        this.userAge = userAge
    }

    internal fun getUserEmail(): String {
        return userEmail
    }

    internal fun setUserEmail(userEmail: String) {
        this.userEmail = userEmail
    }

    internal fun getUserName(): String {
        return userName
    }

    internal fun setUserName(userName: String) {
        this.userName = userName
    }
}
