package com.bside.v8.domain.member.application

import com.bside.v8.domain.member.dao.persistence.SignUpPersistenceAdapter
import com.bside.v8.domain.member.dto.command.RegisterCommand
import org.springframework.stereotype.Service

@Service
class SignUpService(
    private val signUpPersistenceAdapter: SignUpPersistenceAdapter
) {

    fun register(command: RegisterCommand): String =
        signUpPersistenceAdapter.signUp(command.newUser())
}
