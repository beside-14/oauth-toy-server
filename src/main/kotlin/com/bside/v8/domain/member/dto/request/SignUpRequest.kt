package com.bside.v8.domain.member.dto.request

import com.bside.v8.domain.member.dto.command.SignUpCommand
import com.bside.v8.domain.model.Platform

data class SignUpRequest(
    val nickName: String,
    val email: String,
    val password: String,
    val platform: Platform
) {

    fun command(): SignUpCommand = SignUpCommand(nickName, email, password, platform)
}
