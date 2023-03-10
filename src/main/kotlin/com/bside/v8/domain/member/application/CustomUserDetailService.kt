package com.bside.v8.domain.member.application

import com.bside.v8.domain.member.domain.CustomUserDetail
import com.bside.v8.domain.member.exception.MemberNotFoundException
import com.bside.v8.domain.member.persistence.MemberRepository
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class CustomUserDetailService(
    private val memberRepository: MemberRepository
) : UserDetailsService {
    @Transactional(readOnly = true)
    override fun loadUserByUsername(username: String?): UserDetails {
        val member = memberRepository.findByEmail(
            username ?: throw IllegalArgumentException("이메일을 입력해 주세요.")
        ).orElseThrow { MemberNotFoundException("찾을 수 없는 회원 입니다.") }

        return CustomUserDetail(member)
    }
}
