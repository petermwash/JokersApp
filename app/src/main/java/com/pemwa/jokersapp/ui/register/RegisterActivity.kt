package com.pemwa.jokersapp.ui.register

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.annotation.StringRes
import com.pemwa.jokersapp.R
import com.pemwa.jokersapp.common.EMAIL_REGEX
import com.pemwa.jokersapp.common.onClick
import com.pemwa.jokersapp.common.onTextChanged
import com.pemwa.jokersapp.registerPresenter
import com.pemwa.jokersapp.ui.main.MainActivity
import kotlinx.android.synthetic.main.activity_register.*
import java.util.regex.Pattern

class RegisterActivity : AppCompatActivity(), RegisterView {

    private val presenter by lazy { registerPresenter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        presenter.setView(this)
        initUi()
    }

    private fun initUi() {
        usernameInput.onTextChanged { presenter.onUsernameChanged(it) }
        emailInput.onTextChanged { presenter.onEmailChanged(it) }
        passwordInput.onTextChanged { presenter.onPasswordChanged(it) }
        repeatPasswordInput.onTextChanged { presenter.onRepeatPasswordChanged(it) }

        registerButton.onClick { presenter.onRegisterTapped() }
    }

    enum class EmailErrorType(@StringRes val errorResource: Int) {
        NONE(0),
        EMPTY(R.string.email_empty_error),
        INVALID(R.string.email_error)
    }

    fun getError(context: Context, resource: Int) = if (resource==0) null else context.getString(resource)

    private fun onEmailChanged(email: String): EmailErrorType = when {
        email.isEmpty() -> EmailErrorType.EMPTY
        !Pattern.matches(EMAIL_REGEX, email) -> EmailErrorType.INVALID
        else -> EmailErrorType.NONE
    }

    override fun onRegisterSuccess() = startActivity(MainActivity.getLaunchIntent(this))

    override fun showSignUpError() {
    }

    override fun showUsernameError() {
        usernameInput.error = getString(R.string.username_error)
    }

    override fun showEmailError() {
        emailInput.error = getString(R.string.email_error)
    }

    override fun showPasswordError() {
        passwordInput.error = getString(R.string.password_error)
    }

    override fun showPasswordMatchingError() {
        repeatPasswordInput.error = getString(R.string.repeat_password_error)
    }
}
