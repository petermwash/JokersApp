package com.pemwa.jokersapp.firebase.database

import com.pemwa.jokersapp.model.Joke
import com.pemwa.jokersapp.model.User

interface FirebaseDatabaseInterface {

    fun listenToJokes(onResult: (Joke) -> Unit)

    fun addNewJoke(joke: Joke, onResult: (Boolean) -> Unit)

    fun getFavoriteJokes(userId: String, onResult: (List<Joke>) -> Unit)

    fun changeJokeFavoriteStatus(joke: Joke, userId: String)

    fun createUser(id: String, name: String, email: String)

    fun getProfile(id: String, onResult: (User) -> Unit)
}