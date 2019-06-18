package com.pemwa.jokersapp.presentation.implementation

import com.pemwa.jokersapp.firebase.authentication.FirebaseAuthenticationInterface
import com.pemwa.jokersapp.firebase.database.FirebaseDatabaseInterface
import com.pemwa.jokersapp.model.Joke
import com.pemwa.jokersapp.presentation.AllJokesPresenter
import com.pemwa.jokersapp.ui.jokes.all.AllJokesView
import javax.inject.Inject

class AllJokesPresenterImpl @Inject constructor(
    private val authenticationInterface: FirebaseAuthenticationInterface,
    private val databaseInterface: FirebaseDatabaseInterface
) : AllJokesPresenter {

    private lateinit var view: AllJokesView

    override fun setView(view: AllJokesView) {
        this.view = view
    }

    override fun viewReady() {
        getAllJokes()
    }

    override fun getAllJokes() = databaseInterface.listenToJokes { view.addJoke(it) }

    override fun onFavoriteButtonTapped(joke: Joke) {
        databaseInterface.changeJokeFavoriteStatus(joke, authenticationInterface.getUserId())
    }
}
