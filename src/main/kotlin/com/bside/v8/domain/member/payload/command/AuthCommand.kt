package com.bside.v8.domain.member.payload.command

import com.bside.v8.global.validator.SelfValidator
import jakarta.validation.constraints.NotBlank

data class AuthCommand(
    @NotBlank(message = "이메일을 입력해 주세요.")
    val email: String,
    @NotBlank(message = "비밀번호를 입력해 주세요.")
    val password: String
) : SelfValidator() {

    init {
        validation()
    }
}
