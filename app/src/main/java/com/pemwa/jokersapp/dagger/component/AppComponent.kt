package com.pemwa.jokersapp.dagger.component

import com.pemwa.jokersapp.dagger.module.PresentationModule
import com.pemwa.jokersapp.presentation.*
import dagger.Component
import javax.inject.Singleton

/**
 * Created by petermwash on 15/05/19.
 */

@Component(modules = [PresentationModule::class])
@Singleton
interface AppComponent {

    fun registerPresenter(): RegisterPresenter

    fun loginPresenter(): LoginPresenter

    fun allJokesPresenter(): AllJokesPresenter

    fun favoriteJokesPresenter(): FavoriteJokesPresenter

    fun profilePresenter(): ProfilePresenter

    fun addJokePresenter(): AddJokePresenter

    fun welcomePresenter(): WelcomePresenter
}