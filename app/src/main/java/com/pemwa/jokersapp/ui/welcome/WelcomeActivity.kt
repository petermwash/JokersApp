package com.pemwa.jokersapp.ui.welcome

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.pemwa.jokersapp.R
import com.pemwa.jokersapp.common.onClick
import com.pemwa.jokersapp.ui.login.LoginActivity
import com.pemwa.jokersapp.ui.main.MainActivity
import com.pemwa.jokersapp.ui.register.RegisterActivity
import com.pemwa.jokersapp.welcomePresenter
import kotlinx.android.synthetic.main.activity_welcome.*

class WelcomeActivity : AppCompatActivity(), WelcomeView {

    private val presenter by lazy { welcomePresenter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)
        presenter.setView(this)

        presenter.viewReady()

        registerButton.onClick { startActivity(Intent(this, RegisterActivity::class.java)) }
        loginButton.onClick { startActivity(Intent(this, LoginActivity::class.java)) }
    }

    override fun startMainScreen() = startActivity(MainActivity.getLaunchIntent(this))
}
