package com.bside.v8.domain.member.application

import com.bside.v8.domain.member.domain.MemberDetails
import com.bside.v8.domain.member.exception.AlreadyExistException
import com.bside.v8.domain.member.payload.command.AuthCommand
import com.bside.v8.domain.member.payload.command.SignUpCommand
import com.bside.v8.domain.member.persistence.MemberRepository
import com.bside.v8.domain.member.persistence.TokenRepository
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class AuthService(
    private val memberRepository: MemberRepository,
    private val tokenRepository: TokenRepository,
    private val passwordEncoder: PasswordEncoder,
    private val jwtService: JwtService,
    private val authenticationManager: AuthenticationManager
) {

    /**
     * 회원 가입
     */
    fun signUp(command: SignUpCommand): String {
        return when (memberRepository.findByEmail(command.email).isPresent) {
            true -> throw AlreadyExistException()
            false -> {
                val newMember = command.newMember(passwordEncoder.encode(command.password))
                // 회원 등록
                val member =
                    memberRepository.save(newMember)

                // UserDetails
                val memberDetails = MemberDetails(member)

                // JWT 발급
                val jwt = jwtService.generateToken(memberDetails)

                // Token 저장
                saveToken(memberDetails, jwt)

                jwt
            }
        }
    }

    /**
     * 인증
     */
    fun authenticate(command: AuthCommand): String {
        // 인증 유효성 체크
        authenticationManager.authenticate(
            UsernamePasswordAuthenticationToken(command.email, command.password)
        )

        // 회원 조회
        val member =
            memberRepository.findByEmail(command.email)
                .orElseThrow { UsernameNotFoundException("존재하지 않는 회원 입니다.") }

        // UserDetails
        val memberDetails = MemberDetails(member)

        // JWT 발급
        val jwt = jwtService.generateToken(memberDetails)

        // Token 갱신
        revokeAllTokens(member.id)

        // Token 저장
        saveToken(memberDetails, jwt)

        return jwt
    }

    /**
     * 신규 토큰 저장
     */
    private fun saveToken(memberDetails: MemberDetails, jwt: String) {
        tokenRepository.save(memberDetails.newToken(jwt))
    }

    /**
     * 기존 토큰 갱신
     */
    private fun revokeAllTokens(memberId: Long) {
        val validTokens = tokenRepository.findAllValidTokenByMember(memberId)
        if (validTokens.isEmpty()) return
        tokenRepository.saveAll(validTokens.onEach { it.renew() })
    }
}
