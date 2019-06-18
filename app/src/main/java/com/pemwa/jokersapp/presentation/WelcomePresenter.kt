package com.pemwa.jokersapp.presentation

import com.pemwa.jokersapp.ui.welcome.WelcomeView

interface WelcomePresenter : BasePresenter<WelcomeView> {

    fun viewReady()
}
