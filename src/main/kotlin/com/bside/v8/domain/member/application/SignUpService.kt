package com.bside.v8.domain.member.application

import com.bside.v8.domain.member.dao.persistence.SignUpPersistenceAdapter
import com.bside.v8.domain.member.dto.command.SignUpCommand
import org.springframework.stereotype.Service

@Service
class SignUpService(
    private val signUpPersistenceAdapter: SignUpPersistenceAdapter
) {

    fun signUp(command: SignUpCommand): String =
        signUpPersistenceAdapter.signUp(command.newUser())
}
