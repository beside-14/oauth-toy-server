package com.bside.v8.domain.member.application

import com.bside.v8.domain.member.dao.persistence.SignUpPersistenceAdapter
import com.bside.v8.domain.member.dto.command.SignInCommand
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.stereotype.Service

@Service
class SignInService(
    private val authenticationManager: AuthenticationManager,
    private val signUpPersistenceAdapter: SignUpPersistenceAdapter
) {

    fun signIn(command: SignInCommand): String {
        // 사용자 인증
        authenticationManager.authenticate(
            UsernamePasswordAuthenticationToken(
                command.email,
                command.password
            )
        )

        // 유저 조회 및 토큰 생성
        return signUpPersistenceAdapter.signup(command.email)
    }
}
