package com.pemwa.jokersapp.ui.jokes.favorite

import com.pemwa.jokersapp.model.Joke

interface FavoriteView {

    fun showFavoriteJokes(jokes: List<Joke>)

    fun showNoDataDescription()

    fun hideNoDataDescription()

    fun clearItems()
}
