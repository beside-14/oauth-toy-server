package com.bside.v8.auth.adapter.output.mapper

import com.bside.v8.auth.domain.User
import com.bside.v8.user.domain.entity.Member
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Component

@Component
class UserMapper(
    private val passwordEncoder: PasswordEncoder
) {

    fun mapToEntity(user: User): Member = Member(
        nickname = user.nickName,
        email = user.email,
        pw = passwordEncoder.encode(user.password),
        platform = user.platform
    )
}
