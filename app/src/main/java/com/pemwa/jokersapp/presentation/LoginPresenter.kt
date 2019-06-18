package com.pemwa.jokersapp.presentation

import com.pemwa.jokersapp.ui.login.LoginView

interface LoginPresenter : BasePresenter<LoginView> {

    fun onLoginTapped()

    fun onEmailChanged(email: String)

    fun onPasswordChanged(password: String)
}
