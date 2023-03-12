package com.bside.v8.domain.member.payload.request

import com.bside.v8.domain.member.payload.command.AuthCommand

data class AuthRequest(
    val email: String,
    val password: String
) {
    fun command() = AuthCommand(email, password)
}
