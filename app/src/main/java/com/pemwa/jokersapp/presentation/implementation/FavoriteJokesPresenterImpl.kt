package com.pemwa.jokersapp.presentation.implementation

import com.pemwa.jokersapp.firebase.authentication.FirebaseAuthenticationInterface
import com.pemwa.jokersapp.firebase.database.FirebaseDatabaseInterface
import com.pemwa.jokersapp.model.Joke
import com.pemwa.jokersapp.presentation.FavoriteJokesPresenter
import com.pemwa.jokersapp.ui.jokes.favorite.FavoriteView
import javax.inject.Inject

class FavoriteJokesPresenterImpl @Inject constructor(
    private val authenticationInterface: FirebaseAuthenticationInterface,
    private val databaseInterface: FirebaseDatabaseInterface
) : FavoriteJokesPresenter {

    private lateinit var view: FavoriteView

    override fun setView(view: FavoriteView) {
        this.view = view
    }

    override fun getFavoriteJokes() {
        val id = authenticationInterface.getUserId()

        databaseInterface.getFavoriteJokes(id) {
            it.forEach { it.isFavorite = true }

            displayItems(it)
        }
    }

    private fun displayItems(items: List<Joke>) {
        if (items.isEmpty()) {
            view.clearItems()
            view.showNoDataDescription()
        } else {
            view.hideNoDataDescription()
            view.showFavoriteJokes(items)
        }
    }

    override fun onFavoriteButtonTapped(joke: Joke) {
        databaseInterface.changeJokeFavoriteStatus(joke, authenticationInterface.getUserId())
    }
}
