package com.pemwa.jokersapp.ui.jokes.all.list

import android.support.v7.widget.RecyclerView
import android.view.View
import com.pemwa.jokersapp.R
import com.pemwa.jokersapp.common.onClick
import com.pemwa.jokersapp.model.Joke
import kotlinx.android.synthetic.main.item_joke.view.*

class JokeHolder(
    itemView: View,
    private inline val onFavoriteClickHandler: (Joke) -> Unit
) : RecyclerView.ViewHolder(itemView) {

    fun displayData(joke: Joke) = with(itemView) {
        favoriteButton.onClick { onFavoriteClickHandler(joke) }

        jokeAuthor.text = joke.authorName
        jokeDescription.text = joke.text

        favoriteButton.setImageResource(if(joke.isFavorite) R.drawable.ic_favorite_filled else R.drawable.ic_favorite_border)
    }
}
