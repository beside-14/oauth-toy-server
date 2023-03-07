package com.bside.v8.domain.member.dto.request

import com.bside.v8.domain.member.dto.command.SignInCommand

data class SignInRequest(
    val email: String,
    val password: String
) {

    fun command(): SignInCommand = SignInCommand(email, password)
}
