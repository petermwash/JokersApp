package com.pemwa.jokersapp.presentation.implementation

import com.pemwa.jokersapp.common.isValidJoke
import com.pemwa.jokersapp.firebase.authentication.FirebaseAuthenticationInterface
import com.pemwa.jokersapp.firebase.database.FirebaseDatabaseInterface
import com.pemwa.jokersapp.model.Joke
import com.pemwa.jokersapp.presentation.AddJokePresenter
import com.pemwa.jokersapp.ui.add_joke.AddJokeView
import javax.inject.Inject

class AddJokePresenterImpl @Inject constructor(
    private val authenticationInterface: FirebaseAuthenticationInterface,
    private val databaseInterface: FirebaseDatabaseInterface
) : AddJokePresenter {

    private lateinit var view: AddJokeView

    private var jokeText = ""

    override fun setView(view: AddJokeView) {
        this.view = view
    }

    override fun addJokeTapped() {
        if (isValidJoke(jokeText)) {
            val joke = Joke("", authenticationInterface.getUserName(), authenticationInterface.getUserId(), jokeText)

            databaseInterface.addNewJoke(joke) { onAddJokeResult(it) }
        }
    }

    override fun onJokeTextChanged(jokeText: String) {
        this.jokeText = jokeText

        if (!isValidJoke(jokeText)) {
            view.showJokeError()
        } else {
            view.removeJokeError()
        }
    }

    private fun onAddJokeResult(isSuccessful: Boolean) {
        if (isSuccessful) {
            view.onJokeAdded()
        } else {
            view.showAddJokeError()
        }
    }
}
