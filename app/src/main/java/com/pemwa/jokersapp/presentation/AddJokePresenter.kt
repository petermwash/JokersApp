package com.pemwa.jokersapp.presentation

import com.pemwa.jokersapp.ui.add_joke.AddJokeView

interface AddJokePresenter : BasePresenter<AddJokeView> {

    fun addJokeTapped()

    fun onJokeTextChanged(jokeText: String)
}
