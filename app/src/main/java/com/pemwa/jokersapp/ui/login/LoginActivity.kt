package com.pemwa.jokersapp.ui.login

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.pemwa.jokersapp.ui.main.MainActivity
import com.pemwa.jokersapp.R
import com.pemwa.jokersapp.common.onClick
import com.pemwa.jokersapp.common.onTextChanged
import com.pemwa.jokersapp.common.showGeneralError
import com.pemwa.jokersapp.loginPresenter
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity(), LoginView {

    private val presenter by lazy { loginPresenter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        presenter.setView(this)
        initUi()
    }

    private fun initUi() {
        emailInput.onTextChanged { presenter.onEmailChanged(it) }
        passwordInput.onTextChanged { presenter.onPasswordChanged(it) }
        loginButton.onClick { presenter.onLoginTapped() }
    }

    override fun showPasswordError() {
        passwordInput.error = getString(R.string.password_error)
    }

    override fun showEmailError() {
        emailInput.error = getString(R.string.email_error)
    }

    override fun onLoginSuccess() = startActivity(MainActivity.getLaunchIntent(this))

    override fun showLoginError() = showGeneralError(this)
}
