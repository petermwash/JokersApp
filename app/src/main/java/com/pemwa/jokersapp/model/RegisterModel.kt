package com.pemwa.jokersapp.model

import com.pemwa.jokersapp.common.arePasswordsSame
import com.pemwa.jokersapp.common.isEmailValid
import com.pemwa.jokersapp.common.isUsernameValid

data class RegisterRequest(var name: String = "",
                           var email: String = "",
                           var password: String = "",
                           var repeatPassword: String = "") {

    fun isValid(): Boolean = isUsernameValid(name)
            && isEmailValid(email)
            && arePasswordsSame(password, repeatPassword)
}
