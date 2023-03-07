package com.bside.v8.domain.member.dao.persistence

import com.bside.v8.domain.member.dao.repository.MemberRepository
import com.bside.v8.global.annotation.PersistenceAdapter
import com.bside.v8.global.manager.JwtManager

@PersistenceAdapter
class SignUpPersistenceAdapter(
    private val jwtManager: JwtManager,
    private val memberRepository: MemberRepository,
) {

    fun signup(email: String): String {
        val eUser = memberRepository.findByEmail(email).orElseThrow()
        return jwtManager.generateToken(eUser)
    }
}
