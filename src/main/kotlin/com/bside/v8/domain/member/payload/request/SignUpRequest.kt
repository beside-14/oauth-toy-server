package com.bside.v8.domain.member.payload.request

import com.bside.v8.domain.member.payload.command.SignUpCommand
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank

data class SignUpRequest(
        @Email(message = "이메일을 올바른 양식으로 입력해 주세요.")
        @NotBlank
        val email: String,
        @NotBlank(message = "비밀번호를 입력해 주세요.")
        val password: String
) {
    fun command() = SignUpCommand(email, password)
}