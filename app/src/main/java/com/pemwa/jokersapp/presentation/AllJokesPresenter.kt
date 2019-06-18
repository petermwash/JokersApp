package com.pemwa.jokersapp.presentation

import com.pemwa.jokersapp.model.Joke
import com.pemwa.jokersapp.ui.jokes.all.AllJokesView

interface AllJokesPresenter : BasePresenter<AllJokesView> {

    fun viewReady()

    fun getAllJokes()

    fun onFavoriteButtonTapped(joke: Joke)
}
