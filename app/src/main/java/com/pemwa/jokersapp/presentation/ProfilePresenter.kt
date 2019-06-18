package com.pemwa.jokersapp.presentation

import com.pemwa.jokersapp.ui.profile.ProfileView

interface ProfilePresenter : BasePresenter<ProfileView> {

    fun getProfile()

    fun logOut()
}
