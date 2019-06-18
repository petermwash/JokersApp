package com.pemwa.jokersapp.ui.login

interface LoginView {

    fun showPasswordError()

    fun showEmailError()

    fun onLoginSuccess()

    fun showLoginError()
}
