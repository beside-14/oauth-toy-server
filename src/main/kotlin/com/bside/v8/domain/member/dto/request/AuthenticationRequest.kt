package com.bside.v8.domain.member.dto.request

import com.bside.v8.domain.member.dto.command.AuthenticationCommand

data class AuthenticationRequest(
    val email: String,
    val password: String
) {

    fun command(): AuthenticationCommand = AuthenticationCommand(email, password)
}
