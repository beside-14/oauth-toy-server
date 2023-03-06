package com.bside.v8.auth.adapter.input.request

import com.bside.v8.auth.application.port.input.RegisterCommand
import com.bside.v8.global.domain.enumerate.Platform

data class RegisterRequest(
    val nickName: String,
    val email: String,
    val password: String,
    val platform: Platform
) {

    fun command(): RegisterCommand = RegisterCommand(nickName, email, password, platform)
}
