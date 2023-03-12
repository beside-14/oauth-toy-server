package com.bside.v8.domain.member.application

import com.bside.v8.domain.member.domain.Member
import com.bside.v8.domain.member.persistence.MemberRepository
import org.springframework.stereotype.Service
import javax.security.auth.login.AccountNotFoundException

@Service
class MemberService(
    private val memberRepository: MemberRepository
) {

    fun find(memberId: Long): Member =
        memberRepository.findById(memberId).orElseThrow { AccountNotFoundException() }
}
