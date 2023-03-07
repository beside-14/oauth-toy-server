package com.bside.v8.domain.member.dao.persistence

import com.bside.v8.domain.member.dao.repository.MemberRepository
import com.bside.v8.global.annotation.PersistenceAdapter
import com.bside.v8.global.manager.JwtManager

@PersistenceAdapter
class FindAndCreatePersistenceAdapter(
    private val jwtManager: JwtManager,
    private val memberRepository: MemberRepository,
) {

    fun findAndCreate(email: String): String {
        val eUser = memberRepository.findByEmail(email).orElseThrow()
        return jwtManager.generateToken(eUser)
    }
}
