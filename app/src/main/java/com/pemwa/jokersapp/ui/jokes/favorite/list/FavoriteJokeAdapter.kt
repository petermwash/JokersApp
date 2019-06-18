package com.pemwa.jokersapp.ui.jokes.favorite.list

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.pemwa.jokersapp.R
import com.pemwa.jokersapp.model.Joke
import com.pemwa.jokersapp.ui.jokes.all.list.JokeHolder

class FavoriteJokeAdapter(
    private val onFavoriteClickHandler: (Joke) -> Unit
) : RecyclerView.Adapter<JokeHolder>() {

    private val items = mutableListOf<Joke>()

    override fun getItemCount() = items.size

    fun setData(data: List<Joke>) {
        items.clear()
        items.addAll(data)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JokeHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_joke, parent, false)

        return JokeHolder(view, onFavoriteClickHandler)
    }

    override fun onBindViewHolder(holder: JokeHolder, position: Int) = holder.displayData(items[position])

    fun clear() {
        items.clear()
        notifyDataSetChanged()
    }
}
