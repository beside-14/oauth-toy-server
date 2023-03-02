package com.bside.v8.auth.application.port.input

interface RegisterUseCase {

    fun register(command: RegisterCommand): String
}
