package com.bside.v8.domain.member.dao.persistence

import com.bside.v8.domain.member.dao.mapper.MemberMapper
import com.bside.v8.domain.member.dao.repository.MemberRepository
import com.bside.v8.domain.member.dto.domain.MemberDto
import com.bside.v8.global.annotation.PersistenceAdapter
import com.bside.v8.global.manager.JwtManager

@PersistenceAdapter
class SignUpPersistenceAdapter(
    private val jwtManager: JwtManager,
    private val memberRepository: MemberRepository,
    private val memberMapper: MemberMapper
) {

    fun signUp(memberDto: MemberDto): String {
        val eUser = memberRepository.save(memberMapper.mapToEntity(memberDto))
        return jwtManager.generateToken(eUser)
    }
}
