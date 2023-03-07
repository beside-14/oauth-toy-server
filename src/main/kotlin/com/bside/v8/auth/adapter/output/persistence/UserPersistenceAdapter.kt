package com.bside.v8.auth.adapter.output.persistence

import com.bside.v8.auth.adapter.output.mapper.UserMapper
import com.bside.v8.auth.application.port.output.FindAndCreateTokenPort
import com.bside.v8.auth.application.port.output.RegisterUserAndCreateTokenPort
import com.bside.v8.auth.domain.User
import com.bside.v8.global.annotation.PersistenceAdapter
import com.bside.v8.user.domain.repository.MemberRepository
import com.bside.v8.global.manager.JwtManager

@PersistenceAdapter
class UserPersistenceAdapter(
    private val jwtManager: JwtManager,
    private val memberRepository: MemberRepository,
    private val userMapper: UserMapper
) : RegisterUserAndCreateTokenPort,
    FindAndCreateTokenPort {

    override fun registerAndCreate(user: User): String {
        val eUser = memberRepository.save(userMapper.mapToEntity(user))
        return jwtManager.generateToken(eUser)
    }

    override fun findAndCreate(email: String): String {
        val eUser = memberRepository.findByEmail(email).orElseThrow()
        return jwtManager.generateToken(eUser)
    }
}
