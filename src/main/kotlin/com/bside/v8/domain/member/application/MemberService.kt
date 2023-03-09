package com.bside.v8.domain.member.application

import com.bside.v8.domain.member.dao.repository.MemberRepository
import com.bside.v8.domain.member.domain.Member
import com.bside.v8.domain.member.dto.command.SignInCommand
import com.bside.v8.domain.member.dto.command.SignUpCommand
import com.bside.v8.global.manager.JwtManager
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class MemberService(
    private val jwtManager: JwtManager,
    private val memberRepository: MemberRepository,
    private val passwordEncoder: PasswordEncoder
) {

    /**
     * 로그인
     */
    fun signIn(command: SignInCommand): String {
        val member =
            memberRepository.findByEmail(command.email).orElseThrow { IllegalAccessException() }

        return when (passwordEncoder.matches(command.password, member.password)) {
            true -> jwtManager.generateToken(member)
            false -> throw IllegalAccessException()
        }
    }

    /**
     * 회원가입
     */
    fun signUp(command: SignUpCommand): String =
        when (memberRepository.findByEmail(command.email).isPresent) {
            true -> throw IllegalAccessException()
            false -> {
                val newMember = memberRepository.save(
                    Member(
                        nickname = command.nickName,
                        email = command.email,
                        pw = command.password,
                        platform = command.platform
                    )
                )
                jwtManager.generateToken(newMember)
            }
        }
}
