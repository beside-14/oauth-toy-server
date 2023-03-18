package com.bside.v8.domain.member.application

import com.bside.v8.domain.member.domain.MemberRepository
import com.bside.v8.domain.member.exception.MemberInputException
import com.bside.v8.domain.member.payload.command.LoginCommand
import com.bside.v8.domain.member.payload.response.LoginResponse
import com.bside.v8.global.domain.security.JwtProvider
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class LoginService(
        private val memberRepository: MemberRepository,
        private val passwordEncoder: BCryptPasswordEncoder
) {
    fun login(loginCommand: LoginCommand): LoginResponse {
        // 아이디가 존재하는지
        val member = memberRepository.findByEmail(loginCommand.email) ?: throw MemberInputException("존재하지 않는 email입니다.")
        // 패스워드가 일치하는지
        member.matches(loginCommand.password, passwordEncoder)
        return LoginResponse(member.id, member.email, JwtProvider.createToken(member.email, member.role))
    }
}