package com.pemwa.jokersapp.ui.main

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.pemwa.jokersapp.R
import com.pemwa.jokersapp.common.onClick
import com.pemwa.jokersapp.common.onPageChange
import com.pemwa.jokersapp.ui.add_joke.AddJokeActivity
import com.pemwa.jokersapp.ui.jokes.all.AllJokesFragment
import com.pemwa.jokersapp.ui.jokes.favorite.FavoriteJokesFragment
import com.pemwa.jokersapp.ui.main.pager.MainPagerAdapter
import com.pemwa.jokersapp.ui.profile.ProfileFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    companion object {
        fun getLaunchIntent(from: Context) = Intent(from, MainActivity::class.java).apply {
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initUi()
    }

    private fun initUi() {
        val adapter = MainPagerAdapter(supportFragmentManager)
        adapter.setPages(listOf(AllJokesFragment(), FavoriteJokesFragment(), ProfileFragment()))

        mainPager.adapter = adapter
        mainPager.offscreenPageLimit = 3
        bottomNavigation.setOnNavigationItemSelectedListener {
            switchNavigationTab(it.order)
            true
        }

        mainPager.onPageChange { position ->
            val item = bottomNavigation.menu.getItem(position)

            bottomNavigation.selectedItemId = item.itemId
        }

        addJoke.onClick { startActivity(Intent(this, AddJokeActivity::class.java)) }
    }

    private fun switchNavigationTab(position: Int) = mainPager.setCurrentItem(position, true)
}
