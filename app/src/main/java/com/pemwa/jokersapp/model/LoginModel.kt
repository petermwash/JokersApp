package com.pemwa.jokersapp.model

import com.pemwa.jokersapp.common.isEmailValid
import com.pemwa.jokersapp.common.isPasswordValid

data class LoginRequest(var email: String = "",
                        var password: String = "") {

    fun isValid() = isEmailValid(email) && isPasswordValid(password)
}
