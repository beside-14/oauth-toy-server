package com.bside.v8.auth.application.service

import com.bside.v8.auth.application.port.input.RegisterCommand
import com.bside.v8.auth.application.port.input.RegisterUseCase
import com.bside.v8.auth.application.port.output.RegisterUserAndCreateTokenPort
import com.bside.v8.global.annotation.UseCase

@UseCase
class RegisterService(
    private val registerUserAndCreateTokenPort: RegisterUserAndCreateTokenPort
) : RegisterUseCase {

    override fun register(command: RegisterCommand): String =
        registerUserAndCreateTokenPort.registerAndCreate(command.newUser())
}
