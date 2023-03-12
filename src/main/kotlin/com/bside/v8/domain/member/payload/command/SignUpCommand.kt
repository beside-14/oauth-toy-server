package com.bside.v8.domain.member.payload.command

import com.bside.v8.domain.member.domain.Member
import com.bside.v8.domain.member.domain.Platform
import com.bside.v8.global.validator.SelfValidator
import jakarta.validation.constraints.NotBlank

data class SignUpCommand(
    @NotBlank(message = "닉네임을 입력해 주세요.")
    val nickname: String,
    @NotBlank(message = "이메일을 입력해 주세요.")
    val email: String,
    @NotBlank(message = "비밀번호를 입력해 주세요.")
    val password: String,
    val platform: Platform
) : SelfValidator() {

    init {
        validation()
    }

    fun newMember(encodedPassword: String) = Member(
        nickname = nickname,
        email = email,
        password = encodedPassword,
        platform = platform
    )
}
