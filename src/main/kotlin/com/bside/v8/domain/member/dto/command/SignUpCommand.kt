package com.bside.v8.domain.member.dto.command

import com.bside.v8.domain.member.dto.domain.MemberDto
import com.bside.v8.domain.model.Platform

data class SignUpCommand(
    val nickName: String,
    val email: String,
    val password: String,
    val platform: Platform
) {

    fun newUser(): MemberDto = MemberDto(nickName, email, password, platform)
}
