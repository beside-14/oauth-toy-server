package com.bside.v8.auth.adapter.output.persistence

import com.bside.v8.auth.adapter.output.mapper.UserMapper
import com.bside.v8.auth.application.port.output.FindAndCreateTokenPort
import com.bside.v8.auth.application.port.output.RegisterUserAndCreateTokenPort
import com.bside.v8.auth.domain.User
import com.bside.v8.global.annotation.PersistenceAdapter
import com.bside.v8.global.domain.repository.UserRepository
import com.bside.v8.global.manager.JwtManager

@PersistenceAdapter
class UserPersistenceAdapter(
    private val jwtManager: JwtManager,
    private val userRepository: UserRepository,
    private val userMapper: UserMapper
) : RegisterUserAndCreateTokenPort,
    FindAndCreateTokenPort {

    override fun registerAndCreate(user: User): String {
        val eUser = userRepository.save(userMapper.mapToEntity(user))
        return jwtManager.generateToken(eUser)
    }

    override fun findAndCreate(email: String): String {
        val eUser = userRepository.findByEmail(email).orElseThrow()
        return jwtManager.generateToken(eUser)
    }
}
