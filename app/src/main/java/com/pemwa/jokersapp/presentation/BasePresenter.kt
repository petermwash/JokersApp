package com.pemwa.jokersapp.presentation

interface BasePresenter<in T> {

    fun setView(view: T)
}
