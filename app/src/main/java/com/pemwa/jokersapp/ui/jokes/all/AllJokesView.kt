package com.pemwa.jokersapp.ui.jokes.all

import com.pemwa.jokersapp.model.Joke

interface AllJokesView {

    fun showNoDataDescription()

    fun hideNoDataDescription()

    fun addJoke(joke: Joke)

    fun setFavoriteJokesIds(favoriteJokesIds : List<String>)
}
