package com.bside.v8.domain.member.application

import com.bside.v8.domain.member.domain.Member
import com.bside.v8.domain.member.persistence.MemberRepository
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class MemberService(
    private val memberRepository: MemberRepository,
    private val passwordEncoder: PasswordEncoder
) {

    fun signIn(email: String, password: String): String {
        return ""
    }

    fun signUp(member: Member): String {
        return ""
    }
}
