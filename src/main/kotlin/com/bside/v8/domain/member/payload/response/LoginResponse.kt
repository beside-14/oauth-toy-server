package com.bside.v8.domain.member.payload.response

class LoginResponse(
        val id: Long,
        val email: String,
        val token: String
)