package com.pemwa.jokersapp.presentation.implementation

import com.pemwa.jokersapp.common.isEmailValid
import com.pemwa.jokersapp.common.isPasswordValid
import com.pemwa.jokersapp.firebase.authentication.FirebaseAuthenticationInterface
import com.pemwa.jokersapp.model.LoginRequest
import com.pemwa.jokersapp.presentation.LoginPresenter
import com.pemwa.jokersapp.ui.login.LoginView
import javax.inject.Inject

class LoginPresenterImpl @Inject constructor(
    private val authentication: FirebaseAuthenticationInterface
) : LoginPresenter {

    private lateinit var view: LoginView

    private val loginRequest = LoginRequest()

    override fun setView(view: LoginView) {
        this.view = view
    }

    override fun onLoginTapped() {
        if (loginRequest.isValid()) {
            authentication.login(loginRequest.email, loginRequest.password) { isSuccess ->
                if (isSuccess) {
                    view.onLoginSuccess()
                } else {
                    view.showLoginError()
                }
            }
        }
    }

    override fun onEmailChanged(email: String) {
        loginRequest.email = email

        if (!isEmailValid(email)) {
            view.showEmailError()
        }
    }

    override fun onPasswordChanged(password: String) {
        loginRequest.password = password

        if (!isPasswordValid(password)) {
            view.showPasswordError()
        }
    }
}
