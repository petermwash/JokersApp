package com.pemwa.jokersapp.presentation.implementation

import com.pemwa.jokersapp.firebase.authentication.FirebaseAuthenticationInterface
import com.pemwa.jokersapp.presentation.WelcomePresenter
import com.pemwa.jokersapp.ui.welcome.WelcomeView
import javax.inject.Inject

class WelcomePresenterImpl @Inject constructor(
    private val authenticationInterface: FirebaseAuthenticationInterface
) : WelcomePresenter {

    private lateinit var view: WelcomeView

    override fun setView(view: WelcomeView) {
        this.view = view
    }

    override fun viewReady() {
        if (authenticationInterface.getUserId().isNotBlank()) {
            view.startMainScreen()
        }
    }
}
