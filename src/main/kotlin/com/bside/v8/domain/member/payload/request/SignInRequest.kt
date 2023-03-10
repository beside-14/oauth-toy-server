package com.bside.v8.domain.member.payload.request

data class SignInRequest(
    val email: String,
    val password: String
)
