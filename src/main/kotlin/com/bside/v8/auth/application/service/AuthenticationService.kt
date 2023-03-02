package com.bside.v8.auth.application.service

import com.bside.v8.auth.application.port.input.AuthenticationCommand
import com.bside.v8.auth.application.port.input.AuthenticationUseCase
import com.bside.v8.auth.application.port.output.FindAndCreateTokenPort
import com.bside.v8.global.annotation.UseCase
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken

@UseCase
class AuthenticationService(
    private val authenticationManager: AuthenticationManager,
    private val findUserAndCreateTokenPort: FindAndCreateTokenPort
) : AuthenticationUseCase {

    override fun authentication(command: AuthenticationCommand): String {
        // 사용자 인증
        authenticationManager.authenticate(
            UsernamePasswordAuthenticationToken(
                command.email,
                command.password
            )
        )

        // 유저 조회 및 토큰 생성
        return findUserAndCreateTokenPort.findAndCreate(command.email)
    }
}
