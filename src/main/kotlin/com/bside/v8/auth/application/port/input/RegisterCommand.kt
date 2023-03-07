package com.bside.v8.auth.application.port.input

import com.bside.v8.auth.domain.User
import com.bside.v8.user.domain.enumerate.Platform

data class RegisterCommand(
    val nickName: String,
    val email: String,
    val password: String,
    val platform: Platform
) {

    fun newUser(): User = User(nickName, email, password, platform)
}
