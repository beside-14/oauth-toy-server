package com.bside.v8.domain.member.dto.command

data class AuthenticationCommand(
    val email: String,
    val password: String
)
