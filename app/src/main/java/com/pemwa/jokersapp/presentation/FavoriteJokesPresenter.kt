package com.pemwa.jokersapp.presentation

import com.pemwa.jokersapp.model.Joke
import com.pemwa.jokersapp.ui.jokes.favorite.FavoriteView

interface FavoriteJokesPresenter : BasePresenter<FavoriteView> {

    fun getFavoriteJokes()

    fun onFavoriteButtonTapped(joke: Joke)
}
