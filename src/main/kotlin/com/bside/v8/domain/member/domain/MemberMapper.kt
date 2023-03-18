package com.bside.v8.domain.member.domain

import com.bside.v8.domain.member.payload.command.SignUpCommand
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Component

@Component
class MemberMapper(
        private val passwordEncoder: BCryptPasswordEncoder
) {
    fun toMember(signUpCommand: SignUpCommand): Member =
            Member(email = signUpCommand.email,
                    password = passwordEncoder.encode(signUpCommand.password))
}