package com.bside.v8.auth.adapter.output.mapper

import com.bside.v8.auth.domain.User
import com.bside.v8.global.domain.entity.EUser
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Component

@Component
class UserMapper(
    private val passwordEncoder: PasswordEncoder
) {

    fun mapToEntity(user: User): EUser = EUser(
        nickname = user.nickName,
        email = user.email,
        pw = passwordEncoder.encode(user.password),
        platform = user.platform
    )
}
