package com.bside.v8.domain.member.application

import com.bside.v8.domain.member.dao.persistence.FindAndCreatePersistenceAdapter
import com.bside.v8.domain.member.dto.command.AuthenticationCommand
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.stereotype.Service

@Service
class AuthenticationService(
    private val authenticationManager: AuthenticationManager,
    private val findAndCreatePersistenceAdapter: FindAndCreatePersistenceAdapter
) {

    fun authentication(command: AuthenticationCommand): String {
        // 사용자 인증
        authenticationManager.authenticate(
            UsernamePasswordAuthenticationToken(
                command.email,
                command.password
            )
        )

        // 유저 조회 및 토큰 생성
        return findAndCreatePersistenceAdapter.findAndCreate(command.email)
    }
}
