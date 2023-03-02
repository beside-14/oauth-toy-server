package com.bside.v8.auth.adapter.input.request

import com.bside.v8.auth.application.port.input.AuthenticationCommand

data class AuthenticationRequest(
    val email: String,
    val password: String
) {

    fun command(): AuthenticationCommand = AuthenticationCommand(email, password)
}
