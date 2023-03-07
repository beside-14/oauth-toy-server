package com.bside.v8.domain.member.dto.request

import com.bside.v8.domain.member.dto.command.RegisterCommand
import com.bside.v8.domain.model.Platform

data class SignUpRequest(
    val nickName: String,
    val email: String,
    val password: String,
    val platform: Platform
) {

    fun command(): RegisterCommand = RegisterCommand(nickName, email, password, platform)
}
