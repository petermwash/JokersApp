package com.pemwa.jokersapp.ui.profile

interface ProfileView {

    fun showUsername(username: String)

    fun showEmail(email: String)

    fun showNumberOfJokes(jokesCount: Int)

    fun openWelcome()
}
