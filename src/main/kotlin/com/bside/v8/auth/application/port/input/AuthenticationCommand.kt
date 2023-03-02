package com.bside.v8.auth.application.port.input

data class AuthenticationCommand(
    val email: String,
    val password: String
)
