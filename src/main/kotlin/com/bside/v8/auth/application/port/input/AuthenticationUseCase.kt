package com.bside.v8.auth.application.port.input

interface AuthenticationUseCase {

    fun authentication(command: AuthenticationCommand): String
}
