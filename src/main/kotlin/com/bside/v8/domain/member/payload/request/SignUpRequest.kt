package com.bside.v8.domain.member.payload.request

import com.bside.v8.domain.member.domain.Platform
import com.bside.v8.domain.member.payload.command.SignUpCommand

data class SignUpRequest(
    val nickName: String,
    val email: String,
    val password: String,
    val platform: Platform
) {
    fun command() =
        SignUpCommand(
            nickname = nickName,
            email = email,
            password = password,
            platform = platform
        )
}
